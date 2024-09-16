import com.example.bookshelfapp.data.dto.UserIpDetailsDto
import okhttp3.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object IpDetailsApi {

    private val client = OkHttpClient()

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter(UserIpDetailsDto::class.java)

    private val request = Request.Builder()
        .url("http://ip-api.com/json/")
        .addHeader("Accept", "application/json")
        .build()

    suspend fun fetchIpDetails(): UserIpDetailsDto? {
        return suspendCancellableCoroutine { continuation ->
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(e)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val contentType = response.header("Content-Type")
                        if (contentType != null && contentType.contains("application/json")) {
                            val responseData = response.body?.string()
                            try {
                                val ipGeolocationResponse = jsonAdapter.fromJson(responseData)
                                continuation.resume(ipGeolocationResponse)
                            } catch (e: Exception) {
                                continuation.resumeWithException(e)
                            }
                        } else {
                            continuation.resumeWithException(
                                IllegalArgumentException("Unexpected Content-Type: $contentType. Response might be HTML.")
                            )
                        }
                    } else {
                        continuation.resumeWithException(
                            IOException("Request failed with status code ${response.code}")
                        )
                    }
                }
            })
        }
    }
}

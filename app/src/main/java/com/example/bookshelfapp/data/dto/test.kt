import com.example.bookshelfapp.data.dto.CountryListResponseDto
import com.squareup.moshi.JsonClass
import okhttp3.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException


@JsonClass(generateAdapter = true)
data class IpGeolocationResponse(
    val status: String?,
    val country: String?,
    val countryCode: String?,
    val region: String?,
    val regionName: String?,
    val city: String?,
    val zip: String?,
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    val isp: String?,
    val org: String?,
    val `as`: String?,
    val query: String?
)
fun main() {
    val client = OkHttpClient()

    // Setup Moshi and JsonAdapter
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(CountryListResponseDto::class.java)

    val request = Request.Builder()
        .url("https://www.jsonkeeper.com/b/CNGI") // Replace with the correct API endpoint
        .addHeader("Accept", "application/json")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val contentType = response.header("Content-Type")
                if (contentType != null && contentType.contains("application/json")) {
                    val responseData = response.body?.string()
                    try {
                        val ipGeolocationResponse = jsonAdapter.fromJson(responseData)
                        println(ipGeolocationResponse)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("Failed to parse JSON")
                    }
                } else {
                    println("Unexpected Content-Type: $contentType. Response might be HTML.")
                }
            } else {
                println("Request failed with status code ${response.code}")
            }
        }
    })
}

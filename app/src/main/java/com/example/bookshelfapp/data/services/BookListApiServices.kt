package com.example.bookshelfapp.data.services

import com.example.bookshelfapp.data.dto.BookDetailsResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.Response

interface BookListApiServices {
    @GET("b/CNGI/")
    suspend fun getBookDetails(): Response<List<BookDetailsResponseDto>>
}

class BooksApi {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.jsonkeeper.com/") // Base URL for Retrofit
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val service = retrofit.create(BookListApiServices::class.java)

    suspend fun getBookDetails(): List<BookDetailsResponseDto> {
        val response = service.getBookDetails()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            println("Error: ${response.errorBody()?.string()}")
            null
        }?: emptyList()
    }
}
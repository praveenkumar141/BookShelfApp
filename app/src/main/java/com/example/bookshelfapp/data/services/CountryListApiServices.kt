package com.example.bookshelfapp.data.services

import com.example.bookshelfapp.data.dto.CountryListResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface CountryListApiServices {
    @GET("b/IU1K/")
    suspend fun getCountries(): Response<List<CountryListResponseDto>>
}

class CountriesApi {

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

    private val service = retrofit.create(CountryListApiServices::class.java)

    suspend fun getCountriesList(): List<CountryListResponseDto>? {
        val response = service.getCountries()
        return if (response.isSuccessful) {
            response.body() ?: emptyList() // Return the parsed list directly
        } else {
            println("Error: ${response.errorBody()?.string()}")
            null
        }
    }
}
package com.example.bookshelfapp.data.dto

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName

@JsonClass(generateAdapter = true)
data class CountryListResponseDto(
    val country: String? = null,
    val region: String? = null
)

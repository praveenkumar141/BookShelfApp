package com.example.bookshelfapp.data.dto

import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class UserIpDetailsDto(
    val country: String?,
    val status: String?
)

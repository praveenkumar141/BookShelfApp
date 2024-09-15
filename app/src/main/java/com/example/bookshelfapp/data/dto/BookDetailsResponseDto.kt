package com.example.bookshelfapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailsResponseDto(
    @SerialName("id") val id: String,
    @SerialName("image") val image: String,
    @SerialName("score") val score: Double,
    @SerialName("popularity") val popularity: Int,
    @SerialName("title") val title: String,
    @SerialName("publishedChapterDate") val publishedChapterDate: Long
)
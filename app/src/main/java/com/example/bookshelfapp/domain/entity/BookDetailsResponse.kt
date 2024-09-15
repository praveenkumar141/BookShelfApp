package com.example.bookshelfapp.domain.entity

data class BookDetailsResponse(
    val id: String,
    val image: String,
    val score: Double,
    val popularity: Int,
    val title: String,
    val publishedChapterDate: Long
)
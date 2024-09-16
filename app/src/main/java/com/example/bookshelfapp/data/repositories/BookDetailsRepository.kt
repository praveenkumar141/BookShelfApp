package com.example.bookshelfapp.data.repositories

import com.example.bookshelfapp.domain.entity.BookDetailsResponse

interface BookDetailsRepository {
    suspend fun getBookDetails(): List<BookDetailsResponse>
    suspend fun cacheBooks(books: List<BookDetailsResponse>)
    suspend fun clearCache()
}
package com.example.bookshelfapp.data.repositories

import com.example.bookshelfapp.domain.entity.BookDetailsResponse

interface BookDetailsRepository {
    suspend fun getBookDetails(): List<BookDetailsResponse>
    suspend fun bookmarkBook(bookId: String)
    suspend fun unbookmarkBook(bookId: String)
    suspend fun getBookmarkedBooks(userId: String): List<BookDetailsResponse>
}
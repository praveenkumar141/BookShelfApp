package com.example.bookshelfapp.domain.usecases

interface BookMarkUseCase {
    suspend fun bookmarkBook(bookId: String)
    suspend fun unbookmarkBook(bookId: String)
}
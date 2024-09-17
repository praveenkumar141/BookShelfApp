package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.data.repositories.BookDetailsRepository

class BookMarkUseCaseImpl(
    private val repository: BookDetailsRepository
):BookMarkUseCase {
    override suspend fun bookmarkBook(bookId: String) {
        repository.bookmarkBook(bookId)
    }

    override suspend fun unbookmarkBook(bookId: String) {
        repository.unbookmarkBook(bookId)
    }
}
package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.domain.entity.BookDetailsResponse

interface GetBookDetailsUseCase {
    suspend fun getBookDetails(): List<BookDetailsResponse>
}
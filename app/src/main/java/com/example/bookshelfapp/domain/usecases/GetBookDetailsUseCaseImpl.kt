package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.domain.entity.BookDetailsResponse

class GetBookDetailsUseCaseImpl(
    private val bookDetailsRepository: BookDetailsRepository
) : GetBookDetailsUseCase {
    override suspend fun getBookDetails(): List<BookDetailsResponse> {
        println("Books")
        println(bookDetailsRepository.getBookDetails())
        return bookDetailsRepository.getBookDetails()
    }
}
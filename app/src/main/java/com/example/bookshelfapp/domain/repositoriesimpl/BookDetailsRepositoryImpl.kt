package com.example.bookshelfapp.domain.repositoriesimpl

import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.data.services.BooksApi
import com.example.bookshelfapp.domain.entity.BookDetailsResponse

class BookDetailsRepositoryImpl(
    private val bookListApi: BooksApi
) : BookDetailsRepository {
    override suspend fun getBookDetails(): List<BookDetailsResponse> {
        return bookListApi.getBookDetails().map {
            BookDetailsResponse(
                id = it.id,
                image = it.image,
                score = it.score,
                popularity = it.popularity,
                title = it.title,
                publishedChapterDate = it.publishedChapterDate
            )
        }
    }
}
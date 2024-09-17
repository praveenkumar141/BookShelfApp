package com.example.bookshelfapp.domain.repositoriesimpl

import android.content.Context
import com.example.bookshelfapp.data.local.BookDao
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.DatabaseProvider
import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.data.services.BooksApi
import com.example.bookshelfapp.domain.entity.BookDetailsResponse

class BookDetailsRepositoryImpl(
    private val bookListApi: BooksApi,
    context: Context
) : BookDetailsRepository {
    private val bookDao: BookDao = DatabaseProvider.getDatabase(context).bookDao()
    override suspend fun getBookDetails(): List<BookDetailsResponse> {
        val cachedBooks = bookDao.getAllBooks()
        if (cachedBooks.isNotEmpty()) {
            return cachedBooks.map { it.toDomainModel() }
        }
        val response = bookListApi.getBookDetails().map {
            BookDetailsResponse(
                id = it.id,
                image = it.image,
                score = it.score,
                popularity = it.popularity,
                title = it.title,
                publishedChapterDate = it.publishedChapterDate
            )
        }
        cacheBooks(response)
        return response
    }

     private suspend fun cacheBooks(books: List<BookDetailsResponse>) {
        bookDao.insertBooks(books.map { it.toEntity() })
    }
}

fun BookEntity.toDomainModel(): BookDetailsResponse {
    return BookDetailsResponse(
        id = this.id,
        image = this.image,
        score = this.score,
        popularity = this.popularity,
        title = this.title,
        publishedChapterDate = this.publishedChapterDate
    )
}

fun BookDetailsResponse.toEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        image = this.image,
        score = this.score,
        popularity = this.popularity,
        title = this.title,
        publishedChapterDate = this.publishedChapterDate
    )
}

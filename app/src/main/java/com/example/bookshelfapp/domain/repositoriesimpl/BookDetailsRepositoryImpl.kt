package com.example.bookshelfapp.domain.repositoriesimpl

import android.content.Context
import com.example.bookshelfapp.data.mappers.BooksMapper.toDomainModel
import com.example.bookshelfapp.data.mappers.BooksMapper.toEntity
import com.example.bookshelfapp.data.local.BookDao
import com.example.bookshelfapp.data.local.DatabaseProvider
import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.data.services.BooksApi
import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

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

    override suspend fun bookmarkBook(bookId: String) {
        val book = bookDao.getBookById(bookId)
        if (book != null) {
            bookDao.updateBookmarkStatus(bookId, true)
        }
    }

    override suspend fun unbookmarkBook(bookId: String) {
        val book = bookDao.getBookById(bookId)
        if (book != null) {
            bookDao.updateBookmarkStatus(bookId, false)
        }
    }

    override suspend fun getBookmarkedBooks(userId: String): List<BookDetailsResponse> {
        return bookDao.getBookmarkedBooks()
            .map { entities -> entities.map { it.toDomainModel() } }
            .first()
    }

     private suspend fun cacheBooks(books: List<BookDetailsResponse>) {
        bookDao.insertBooks(books.map { it.toEntity() })
    }
}

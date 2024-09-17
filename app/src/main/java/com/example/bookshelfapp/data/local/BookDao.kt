package com.example.bookshelfapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("SELECT * FROM book_details")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM book_details WHERE id = :bookId")
    suspend fun getBookById(bookId: String): BookEntity?

    @Query("UPDATE book_details SET isBookmarked = :isBookmarked WHERE id = :bookId")
    suspend fun updateBookmarkStatus(bookId: String, isBookmarked: Boolean)

    @Query("SELECT * FROM book_details WHERE isBookmarked = 1")
    fun getBookmarkedBooks(): Flow<List<BookEntity>>

    @Query("DELETE FROM book_details")
    suspend fun clearBooks()
}

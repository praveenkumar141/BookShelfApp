package com.example.bookshelfapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("SELECT * FROM book_details")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("DELETE FROM book_details")
    suspend fun clearBooks()
}

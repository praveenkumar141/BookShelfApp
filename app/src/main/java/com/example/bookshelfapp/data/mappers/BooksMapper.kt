package com.example.bookshelfapp.data.mappers

import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.domain.entity.BookDetailsResponse

object BooksMapper {
    fun BookEntity.toDomainModel(): BookDetailsResponse {
        return BookDetailsResponse(
            id = this.id,
            image = this.image,
            score = this.score,
            popularity = this.popularity,
            title = this.title,
            publishedChapterDate = this.publishedChapterDate,
            isBookmarked = this.isBookmarked
        )
    }

    fun BookDetailsResponse.toEntity(): BookEntity {
        return BookEntity(
            id = this.id,
            image = this.image,
            score = this.score,
            popularity = this.popularity,
            title = this.title,
            publishedChapterDate = this.publishedChapterDate,
            isBookmarked = this.isBookmarked
        )
    }
}
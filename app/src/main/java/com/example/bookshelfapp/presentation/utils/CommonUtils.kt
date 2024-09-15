package com.example.bookshelfapp.presentation.utils

import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import java.util.Calendar

fun isPasswordValid(password: String): Boolean {
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { it in "!@#$%^&*()," }
    return password.length >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar
}

fun main() {
    println(isEmailValid("pkumar230901@gmail.com"))
}
fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return email.matches(emailRegex.toRegex())
}

fun extractYearFromTimestamp(timestamp: Long): Int {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = timestamp
    }
    return calendar.get(Calendar.YEAR)
}

fun groupBooksByYear(books: List<BookDetailsResponse>): Map<Int, List<BookDetailsResponse>> {
    return books.groupBy { extractYearFromTimestamp(it.publishedChapterDate) }
}


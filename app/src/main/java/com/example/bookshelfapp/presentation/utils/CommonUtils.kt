package com.example.bookshelfapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId

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

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toYear(): Int {
    val epochMillis = this * 1000
    return Instant.ofEpochMilli(epochMillis)
        .atZone(ZoneId.systemDefault())
        .year
}

fun getRatingOutOf5(score: Double): Double {
    val maxScore = 50.0
    return ((score / maxScore) * 5).coerceAtMost(5.0)
}

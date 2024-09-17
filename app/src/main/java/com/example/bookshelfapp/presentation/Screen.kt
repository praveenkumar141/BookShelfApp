package com.example.bookshelfapp.presentation

sealed class Screen(val route: String) {
    data object SignUpScreen : Screen("signup_screen")
    data object LoginScreen : Screen("login_screen")
}
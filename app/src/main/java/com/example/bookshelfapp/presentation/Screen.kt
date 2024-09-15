package com.example.bookshelfapp.presentation

sealed class Screen(val route: String) {
    object SignUpScreen : Screen("signup_screen")
    object LoginScreen : Screen("login_screen")
    object BookListScreen : Screen("booklist_screen")
}
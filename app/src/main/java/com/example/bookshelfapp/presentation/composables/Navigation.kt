package com.example.bookshelfapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelfapp.presentation.Screen
import com.example.bookshelfapp.presentation.composables.screens.BookListScreen
import com.example.bookshelfapp.presentation.composables.screens.LoginScreen
import com.example.bookshelfapp.presentation.composables.screens.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.BookListScreen.route, builder = {
        composable(Screen.SignUpScreen.route, content = { SignUpScreen(navController) })
        composable(Screen.LoginScreen.route, content = { LoginScreen(navController) })
        composable(Screen.BookListScreen.route, content = { BookListScreen() })
    })
}
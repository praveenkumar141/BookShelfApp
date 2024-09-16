package com.example.bookshelfapp.presentation.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelfapp.presentation.Screen
import com.example.bookshelfapp.presentation.composables.screens.LoginScreen
import com.example.bookshelfapp.presentation.composables.screens.SignUpScreen
import com.example.bookshelfapp.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(onAuthenticateSuccess: () -> Unit) {
    val navController = rememberNavController()
    val viewModel: AuthViewModel = koinViewModel()

    NavHost(navController, startDestination = Screen.LoginScreen.route, builder = {
        composable(Screen.SignUpScreen.route, content = { SignUpScreen(viewModel, navController,onAuthenticateSuccess) })
        composable(Screen.LoginScreen.route, content = { LoginScreen(viewModel,navController,onAuthenticateSuccess) })
    })
}
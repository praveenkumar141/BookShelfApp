package com.example.bookshelfapp.presentation.composables.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookshelfapp.R
import com.example.bookshelfapp.presentation.Screen
import com.example.bookshelfapp.presentation.viewmodel.AuthViewModel
import com.example.bookshelfapp.presentation.composables.BoldTextHeader
import com.example.bookshelfapp.presentation.composables.EmailTextField
import com.example.bookshelfapp.presentation.composables.NormalTextHeader
import com.example.bookshelfapp.presentation.composables.PasswordTextField
import com.example.bookshelfapp.presentation.utils.isEmailValid
import com.example.bookshelfapp.presentation.utils.isPasswordValid

@Composable
fun LoginScreen(viewModel: AuthViewModel, onAuthenticatSuccess: () -> Unit) {
    var text by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(80.dp))
            NormalTextHeader(text = stringResource(id = R.string.Hello))
            BoldTextHeader(text = stringResource(id = R.string.welcome))
            Spacer(Modifier.height(40.dp))
            EmailTextField(
                onEmailChange = { emailText = it }
            )
            PasswordTextField(
                onPasswordChange = { text = it }
            )

            Spacer(Modifier.height(40.dp))
            Button(
                onClick = {
                    val savedEmail = viewModel.getLoginEmail()
                    val savedPassword = viewModel.getLoginPassword()
                    if (emailText == savedEmail && text == savedPassword) {
                        onAuthenticatSuccess()
                    } else {
                        Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }

                },
                enabled = isPasswordValid(text) && isEmailValid(emailText)
            ) {
                Text("Login")
            }
        }
    }

}
package com.example.bookshelfapp.presentation.composables.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bookshelfapp.R
import com.example.bookshelfapp.presentation.Screen
import com.example.bookshelfapp.presentation.viewmodel.AuthViewModel
import com.example.bookshelfapp.presentation.composables.BoldTextHeader
import com.example.bookshelfapp.presentation.composables.CountryDropDown
import com.example.bookshelfapp.presentation.composables.EmailTextField
import com.example.bookshelfapp.presentation.composables.NormalTextHeader
import com.example.bookshelfapp.presentation.composables.PasswordTextField
import com.example.bookshelfapp.presentation.utils.isEmailValid
import com.example.bookshelfapp.presentation.utils.isPasswordValid
import com.example.bookshelfapp.ui.theme.Tangerine

@Composable
fun LoginScreen(viewModel: AuthViewModel,navController: NavHostController, onAuthenticateSuccess: () -> Unit) {
    val context = LocalContext.current
    var passtext by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bookshelfbg),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))
            BookAppText()
            Spacer(Modifier.height(50.dp))
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NormalTextHeader(text = stringResource(id = R.string.Hello))
                BoldTextHeader(text = stringResource(id = R.string.welcome))
                Spacer(Modifier.height(40.dp))
                EmailTextField(
                    onEmailChange = { emailText = it }
                )
                PasswordTextField(
                    onPasswordChange = { passtext = it }
                )
                Spacer(Modifier.height(40.dp))
                OutlinedButton(
                    onClick = {
                        val savedEmail = viewModel.getLoginEmail()
                        val savedPassword = viewModel.getLoginPassword()
                        println(savedPassword)
                        if (emailText == savedEmail && passtext == savedPassword) {
                            onAuthenticateSuccess()
                        } else {
                            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }

                    }, modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        backgroundColor = Tangerine,
                        disabledBackgroundColor = Color.Gray,
                        disabledContentColor = Color.Black
                    ),
                    enabled = isPasswordValid(passtext) && isEmailValid(emailText)
                ) {
                    Text("Sign In", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
                Row {
                    Text("New User?  ", color = Color.White,fontFamily = FontFamily.Serif)
                    Text(
                        "Sign Up",
                        color = Color.White,fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,    textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(Screen.SignUpScreen.route){launchSingleTop = true}
                        }
                    )
                }
            }
        }
    }
}
package com.example.bookshelfapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
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

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel,
    navController: NavHostController,
    onAuthenticateSuccess: () -> Unit
) {
    val countryList = viewModel.countriesList.collectAsState().value
    val ipDetails = viewModel.ipDetails.collectAsState().value
    var passtext by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
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
            BoldTextHeader(text = stringResource(id = R.string.create_account))
            Spacer(Modifier.height(40.dp))
            EmailTextField(
                onEmailChange = { emailText = it }
            )
            PasswordTextField(
                onPasswordChange = { passtext = it }
            )
            if (countryList.isNotEmpty() && ipDetails.isNotEmpty()) CountryDropDown(countryList.sortedWith(
                compareBy { it.country }), ipDetails
            )
            Spacer(Modifier.height(40.dp))
            Text("Already have an account? ")
            Text(
                "Sign in",
                color = Color.Blue,
                modifier = Modifier.clickable { navController.navigate(Screen.LoginScreen.route) }
            )
            Button(
                onClick = {
                    viewModel.saveLogin(emailText, passtext)
                    onAuthenticateSuccess()
                    println("did authenticate")
                },
                enabled = isPasswordValid(passtext) && isEmailValid(emailText)
            ) {
                Text("SignUp")
            }
        }
    }
}

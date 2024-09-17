package com.example.bookshelfapp.presentation.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bookshelfapp.R
import com.example.bookshelfapp.presentation.Screen
import com.example.bookshelfapp.presentation.composables.BoldTextHeader
import com.example.bookshelfapp.presentation.composables.CountryDropDown
import com.example.bookshelfapp.presentation.composables.EmailTextField
import com.example.bookshelfapp.presentation.composables.NormalTextHeader
import com.example.bookshelfapp.presentation.composables.PasswordTextField
import com.example.bookshelfapp.presentation.utils.isEmailValid
import com.example.bookshelfapp.presentation.utils.isPasswordValid
import com.example.bookshelfapp.presentation.viewmodel.AuthViewModel
import com.example.bookshelfapp.ui.theme.Tangerine

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
                BoldTextHeader(text = stringResource(id = R.string.create_account))
                Spacer(Modifier.height(40.dp))
                EmailTextField(
                    onEmailChange = { emailText = it }
                )
                PasswordTextField(
                    onPasswordChange = { passtext = it }
                )
                Spacer(Modifier.height(10.dp))
                if (countryList.isNotEmpty() && ipDetails.isNotEmpty()) CountryDropDown(
                    countryList.sortedWith(
                        compareBy { it.country }), ipDetails
                )
                Spacer(Modifier.height(40.dp))
                OutlinedButton(
                    onClick = {
                        viewModel.saveLogin(emailText, passtext)
                        onAuthenticateSuccess()
                    }, modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        backgroundColor = Tangerine,
                        disabledBackgroundColor = Color.Gray,
                        disabledContentColor = Color.Black
                    ),
                    enabled = isPasswordValid(passtext) && isEmailValid(emailText)
                ) {
                    Text(stringResource(R.string.SignUp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(20.dp))
                Row {
                    Text("Already have an account?  ", color = Color.White,fontFamily = FontFamily.Serif)
                    Text(
                        stringResource(R.string.SignIn),
                        color = Color.White,fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,    textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(Screen.LoginScreen.route){launchSingleTop = true} }
                    )
                }
            }
        }
    }
}

@Composable
fun BookAppText() {
    Row {
        Text(
            modifier = Modifier
                .heightIn(),
            text = stringResource(id = R.string.Book),
            style = TextStyle(
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            ), textAlign = TextAlign.Center, color = Color.White
        )
        Text(
            modifier = Modifier
                .heightIn(),
            text = stringResource(id = R.string.Shelf),
            style = TextStyle(
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            ), textAlign = TextAlign.Center, color = Tangerine
        )
    }
}

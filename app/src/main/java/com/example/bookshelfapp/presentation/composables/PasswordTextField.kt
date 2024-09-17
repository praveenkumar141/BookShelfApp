package com.example.bookshelfapp.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.theme.BgColor
import com.example.bookshelfapp.ui.theme.Purple40

@Composable
fun PasswordTextField(onPasswordChange: (String) -> Unit,) {
    var password by remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        cursorColor = Color.Black,
        focusedBorderColor = Purple40,
        focusedLabelColor = Color.White,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = BgColor,
        unfocusedLabelColor = Color.Gray
    )

    OutlinedTextField(
        value = password,
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp)),
        onValueChange = {
            password = it
            onPasswordChange(it)
        },
        label = { Text(stringResource(R.string.password),fontFamily = FontFamily.Serif) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = textFieldColors,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.padlock),
                contentDescription = "email icon",
            )
        },
        visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val iconImage = if (passwordVisibility.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }
            ) {
                Icon(
                    imageVector = iconImage, contentDescription = null
                )
            }
        }
    )

    Spacer(Modifier.height(20.dp))
}

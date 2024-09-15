package com.example.bookshelfapp.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.theme.BgColor
import com.example.bookshelfapp.ui.theme.Purple40
import com.example.bookshelfapp.ui.theme.Purple80

@Composable
fun EmailTextField(
    onEmailChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        cursorColor = Color.Gray,
        focusedBorderColor = Purple40,
        focusedLabelColor = Purple80,
        unfocusedContainerColor = BgColor
    )

    OutlinedTextField(
        value = text,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp)),
        onValueChange = {
            text = it
            onEmailChange(it)
        },
        label = { Text(stringResource(R.string.email)) },
        colors = textFieldColors,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.email),
                contentDescription = "email icon",
            )
        }
    )

    Spacer(Modifier.height(20.dp))
}

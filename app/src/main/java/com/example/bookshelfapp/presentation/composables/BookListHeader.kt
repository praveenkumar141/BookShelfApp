package com.example.bookshelfapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.theme.Tangerine

@Composable
fun BookListHeader(onLogout: () -> Unit) {
    Column(Modifier.height(120.dp)
        .fillMaxWidth()
        .background(Color.Black)) {
        Spacer(Modifier.height(20.dp))
        Row(
            Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.book_shelf_header),
                contentDescription = "email icon",
            )
            Spacer(Modifier.width(8.dp))
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
            Spacer(Modifier.width(40.dp))
            IconButton(onClick = { onLogout() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "logout icon",
                    tint = Color.White
                )
            }

        }
    }
}
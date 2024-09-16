package com.example.bookshelfapp.presentation.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookshelfapp.R
import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import com.example.bookshelfapp.presentation.utils.getRatingOutOf5
import com.example.bookshelfapp.presentation.utils.toYear
import com.example.bookshelfapp.presentation.viewmodel.BookDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@Composable
fun BookListScreen() {
    Spacer(Modifier.height(60.dp))
    val bookDetailsViewModel: BookDetailsViewModel = koinViewModel()
    val bookDetails by bookDetailsViewModel.bookDetails.collectAsState()
    val yearsWithBooks = bookDetailsViewModel.getYearsWithBooks()
    var selectedYear by remember(bookDetails) { mutableIntStateOf(yearsWithBooks.firstOrNull() ?: 0) }
    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                val firstVisibleBook = bookDetails.getOrNull(index)
                val visibleYear = firstVisibleBook?.publishedChapterDate?.toYear()
                visibleYear?.let { selectedYear = it }
            }
    }

    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        // Year Tabs
        Spacer(Modifier.height(60.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().background(Color.Blue),
            state = rememberLazyListState()
        ) {
            items(yearsWithBooks) { year ->
                TabItem(
                    year = year,
                    isSelected = year == selectedYear,
                    onClick = { selectedYear = year }
                )
            }
        }
        Spacer(Modifier.height(40.dp))

        // Books List
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(bookDetails) { index, book ->
                if (book.publishedChapterDate.toYear() == selectedYear) {
                    BookItem(index = index, book = book)
                }
            }
        }
    }
}

@Composable
fun TabItem(year: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column {
        Text(
            text = year.toString(),
            fontSize = 24.sp,
            fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onClick() },
            color = if (isSelected) Color.White else Color.Gray
        )
       if(isSelected) Divider(Modifier.width(70.dp), thickness = 4.dp, color = Color.Green)
    }

}

@Composable
fun BookItem(index: Int, book: BookDetailsResponse) {
    // Display your book item UI here
    var isBookMarked by remember(index) { mutableStateOf(false) }
    Row(
        Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()) {
        AsyncImage(
            model = book.image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.movies),
            error = painterResource(id = R.drawable.movies)
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = book.title,
                Modifier.width(200.dp),
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = String.format("Rating %.1f/5", getRatingOutOf5(book.score)),
                fontSize = 14.sp
            )
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            val iconImage =
                if (isBookMarked) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks
            IconButton(onClick = { isBookMarked = !isBookMarked }) {
                Icon(imageVector = iconImage, contentDescription = null)
            }
        }

    }
    Spacer(Modifier.height(10.dp))
}

package com.example.bookshelfapp.presentation.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import com.example.bookshelfapp.presentation.utils.groupBooksByYear
import com.example.bookshelfapp.presentation.viewmodel.BookDetailsViewModel
import org.koin.androidx.compose.getViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.pagerTabIndicatorOffset
import androidx.compose.material3.TabRowDefaults
import coil.compose.AsyncImage
import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookListScreen() {
    val viewModel: BookDetailsViewModel = koinViewModel()
    val books = viewModel.bookDetails.collectAsState().value
    val booksByYear = remember { groupBooksByYear(books) }
    val years = booksByYear.keys.sortedDescending() // Sorted years for tabs
    val pagerState: PagerState = rememberPagerState(initialPage = 0, pageCount = {years.size})
    val coroutineScope = rememberCoroutineScope()
    LazyColumn {
        items(books) { book ->
            // Your book item UI here
            Row {
                AsyncImage(
                    model = book.image,
                    contentDescription = null,
                )
                Spacer(Modifier.width(10.dp))
                Text(text = book.title)
            }
            Text(text = book.title)
            Text(text = book.id)
            Text(text = book.popularity.toString())
            Text(text = book.publishedChapterDate.toString())
            Text(text = years.toString())
            Text(text = book.image)
        }
    }
    Column {
        // Tab Row
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                // Custom tab indicator
                TabRowDefaults.Indicator(
//                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            years.forEachIndexed { index, year ->
                Tab(
                    text = { Text(year.toString()) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }

        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { pageIndex ->
            val year = years[pageIndex]
            val booksForYear = booksByYear[year] ?: emptyList()
            // Display books for the year
            BooksList(booksForYear)
        }
    }
}

@Composable
fun BooksList(books: List<BookDetailsResponse>) {
    LazyColumn {
        items(books) { book ->
            // Your book item UI here
            AsyncImage(
                model = book.image,
                contentDescription = null,
            )
        }
    }
}

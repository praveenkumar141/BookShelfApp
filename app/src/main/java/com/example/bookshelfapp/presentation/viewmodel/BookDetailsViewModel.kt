package com.example.bookshelfapp.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import com.example.bookshelfapp.domain.usecases.GetBookDetailsUseCase
import com.example.bookshelfapp.presentation.utils.toYear
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class BookDetailsViewModel(
    private val getBookDetailsUseCase: GetBookDetailsUseCase
) : ViewModel() {
    private val _bookDetails = MutableStateFlow<List<BookDetailsResponse>>(emptyList())
    val bookDetails: StateFlow<List<BookDetailsResponse>> = _bookDetails

    init {
        fetchBookDetails()
    }

    private fun fetchBookDetails() {
        viewModelScope.launch {
            val bookDetails = getBookDetailsUseCase.getBookDetails() // Fetch books
            _bookDetails.value = bookDetails.sortedByDescending { it.publishedChapterDate.toYear() }
        }
    }

    fun getYearsWithBooks(): List<Int> {
        _bookDetails.value.forEach { println(it.publishedChapterDate.toYear()) }
        return _bookDetails.value
            .map { it.publishedChapterDate.toYear() }.distinct()
    }
}

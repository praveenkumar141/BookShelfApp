package com.example.bookshelfapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.domain.entity.BookDetailsResponse
import com.example.bookshelfapp.domain.usecases.GetBookDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val getBookDetailsUseCase: GetBookDetailsUseCase
) : ViewModel() {
    private val _bookDetails = MutableStateFlow<List<BookDetailsResponse>>(emptyList())
    val bookDetails: StateFlow<List<BookDetailsResponse>> = _bookDetails

    init {
       viewModelScope.launch {
           _bookDetails.value = getBookDetailsUseCase.getBookDetails()
       }
    }

}
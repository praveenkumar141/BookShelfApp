package com.example.bookshelfapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.domain.entity.CountryListResponse
import com.example.bookshelfapp.domain.usecases.GetCountryByIpUseCase
import com.example.bookshelfapp.domain.usecases.GetCountryListUseCase
import com.example.bookshelfapp.presentation.utils.PreferenceHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val getCountryByIpUseCase: GetCountryByIpUseCase,
    application: Application
):ViewModel() {

    private val _countriesList = MutableStateFlow<List<CountryListResponse>>(emptyList())
    val countriesList: StateFlow<List<CountryListResponse>> = _countriesList

    private val _ipDetails = MutableStateFlow("")
    val ipDetails: StateFlow<String> = _ipDetails

    init {
        viewModelScope.launch {
            getCountriesList()
            getIpDetails()
        }
    }

    private suspend fun getCountriesList() {
       _countriesList.value =  getCountryListUseCase.getCountriesList()
    }
    private suspend fun getIpDetails() {
        _ipDetails.value = getCountryByIpUseCase.getCountryByIp()
    }

    private val preferenceHelper = PreferenceHelper(application)

    fun saveLogin(email: String, password: String) {
        preferenceHelper.saveLoginDetails(email, password)
    }

    fun getLoginEmail(): String? {
        return preferenceHelper.getLoginEmail()
    }

    fun getLoginPassword(): String? {
        return preferenceHelper.getLoginPassword()
    }

}
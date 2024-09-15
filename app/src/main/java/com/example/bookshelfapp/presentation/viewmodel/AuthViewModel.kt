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
    private val application: Application
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

    suspend fun getCountriesList() {
       _countriesList.value =  getCountryListUseCase.getCountriesList()
    }
    suspend fun getIpDetails() {
        println("dklfjn ${getCountryByIpUseCase.getCountryByIp()}")
        _ipDetails.value = getCountryByIpUseCase.getCountryByIp()
    }

    private val preferenceHelper = PreferenceHelper(application)

    // Save login details
    fun saveLogin(email: String, password: String) {
        preferenceHelper.saveLoginDetails(email, password)
    }

    // Check if user is logged in
    fun isLoggedIn(): Boolean {
        return preferenceHelper.isLoggedIn()
    }

    // Get saved login details
    fun getLoginEmail(): String? {
        return preferenceHelper.getLoginEmail()
    }

    fun getLoginPassword(): String? {
        return preferenceHelper.getLoginPassword()
    }

    // Logout user
    fun logout() {
        preferenceHelper.clearLoginDetails()
    }

}
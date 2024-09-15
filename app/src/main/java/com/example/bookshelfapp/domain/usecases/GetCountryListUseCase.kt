package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.domain.entity.CountryListResponse

interface GetCountryListUseCase {
    suspend fun getCountriesList(): List<CountryListResponse>
}
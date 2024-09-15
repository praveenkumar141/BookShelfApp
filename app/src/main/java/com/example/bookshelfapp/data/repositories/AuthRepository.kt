package com.example.bookshelfapp.data.repositories

import com.example.bookshelfapp.domain.entity.CountryListResponse

interface AuthRepository {
    suspend fun getCountriesList(): List<CountryListResponse>
    suspend fun getIpDetails(): String
}
package com.example.bookshelfapp.domain.usecases

interface GetCountryByIpUseCase {
    suspend fun getCountryByIp(): String
}
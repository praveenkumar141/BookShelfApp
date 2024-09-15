package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.data.repositories.AuthRepository
import com.example.bookshelfapp.domain.entity.CountryListResponse

class GetCountryListUseCaseImpl(
    private val authRepository: AuthRepository
): GetCountryListUseCase {
    override suspend fun getCountriesList(): List<CountryListResponse> {
        return authRepository.getCountriesList()
    }
}
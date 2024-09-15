package com.example.bookshelfapp.domain.usecases

import com.example.bookshelfapp.data.repositories.AuthRepository

class GetCountryByIpUseCaseImpl(
    private val authRepository: AuthRepository
): GetCountryByIpUseCase {
    override suspend fun getCountryByIp(): String {
        return authRepository.getIpDetails()
    }
}
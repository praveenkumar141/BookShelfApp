package com.example.bookshelfapp.domain.repositoriesimpl

import IpDetailsApi
import com.example.bookshelfapp.data.repositories.AuthRepository
import com.example.bookshelfapp.data.services.CountriesApi
import com.example.bookshelfapp.domain.entity.CountryListResponse

class AuthRepositoryImpl(
    private val countryApi: CountriesApi,
    private val ipApi: IpDetailsApi
) : AuthRepository {
    override suspend fun getCountriesList(): List<CountryListResponse> {
        val response = countryApi.getCountriesList()?.map {
            CountryListResponse(it.country, it.region)
        }
        return response?: emptyList()
    }


    override suspend fun getIpDetails(): String {
        return try {
            val response = ipApi.fetchIpDetails()
            response?.let {
                "${it.country}"
            } ?: "No IP details found"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
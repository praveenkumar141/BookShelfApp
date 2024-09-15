package com.example.bookshelfapp.domain.repositoriesimpl

import IpDetailsApi
import com.example.bookshelfapp.data.dto.CountryListResponseDto
import com.example.bookshelfapp.data.repositories.AuthRepository
import com.example.bookshelfapp.data.services.CountriesApi
import com.example.bookshelfapp.domain.entity.CountryListResponse

class AuthRepositoryImpl(
    private val countryApi: CountriesApi,
    private val ipApi: IpDetailsApi
) : AuthRepository {
    override suspend fun getCountriesList(): List<CountryListResponse> {
        val response = countryApi.getCountriesList()
        println("lllllll $response")
        val x = response?.map {
            CountryListResponse(it.country, it.region)
        }
        println(x)
        if(x==null) return emptyList()
        return x

//        val response = countryApi.getCountriesList() ?: return ""
    }


    override suspend fun getIpDetails(): String {
        val res = try {
            val response = ipApi.fetchIpDetails()
            response?.let {
                "${it.country}"
            } ?: "No IP details found"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
        return res
    }
}
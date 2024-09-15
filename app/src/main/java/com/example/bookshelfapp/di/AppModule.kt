package com.example.bookshelfapp.di

import IpDetailsApi
import com.example.bookshelfapp.data.repositories.AuthRepository
import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.data.services.BooksApi
import com.example.bookshelfapp.data.services.CountriesApi
import com.example.bookshelfapp.domain.repositoriesimpl.AuthRepositoryImpl
import com.example.bookshelfapp.domain.repositoriesimpl.BookDetailsRepositoryImpl
import com.example.bookshelfapp.domain.usecases.GetBookDetailsUseCase
import com.example.bookshelfapp.domain.usecases.GetBookDetailsUseCaseImpl
import com.example.bookshelfapp.domain.usecases.GetCountryByIpUseCase
import com.example.bookshelfapp.domain.usecases.GetCountryByIpUseCaseImpl
import com.example.bookshelfapp.domain.usecases.GetCountryListUseCase
import com.example.bookshelfapp.domain.usecases.GetCountryListUseCaseImpl
import com.example.bookshelfapp.presentation.viewmodel.AuthViewModel
import com.example.bookshelfapp.presentation.viewmodel.BookDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory {
        try {
            println("jdhbd Creating Countries()")
            CountriesApi()
        } catch (e: Exception) {
            println("jdhbd Error creating CountriesApi: ${e.message}")
            throw e
        }
    }

    factory {
        try {
            println("jdhbd Creating BookList()")
            BooksApi()
        } catch (e: Exception) {
            println("jdhbd Error creating BookList(): ${e.message}")
            throw e
        }
    }
    single { IpDetailsApi } // Assuming IpDetailsApi is a class or singleton
    factory<AuthRepository> {
        try {
            println("jdhbd Creating AuthRepositoryImpl")
            AuthRepositoryImpl(countryApi = get(), ipApi = get())
        } catch (e: Exception) {
            println("jdhbd Error creating AuthRepositoryImpl: ${e.message}")
            throw e
        }
    }
    factory<BookDetailsRepository> {
        try {
            println("jdhbd Creating BookDetailsRepositoryImpl")
            BookDetailsRepositoryImpl(bookListApi = get())
        } catch (e: Exception) {
            println("jdhbd Error creating BookDetailsRepositoryImpl: ${e.message}")
            throw e
        }
    }

    factory<GetCountryByIpUseCase> {
        try {
            println("jdhbd Creating GetCountryByIpUseCaseImpl")
            GetCountryByIpUseCaseImpl(authRepository = get())
        } catch (e: Exception) {
            println("jdhbd Error creating GetCountryByIpUseCaseImpl: ${e.message}")
            throw e
        }
    }

    factory<GetCountryListUseCase> {
        try {
            println("jdhbd Creating GetCountryListUseCaseImpl")
            GetCountryListUseCaseImpl(authRepository = get())
        } catch (e: Exception) {
            println("jdhbd Error creating GetCountryListUseCaseImpl: ${e.message}")
            throw e
        }
    }

    factory<GetBookDetailsUseCase> {
        try {
            println("jdhbd Creating GetBookDetailsUseCaseImpl")
            GetBookDetailsUseCaseImpl(bookDetailsRepository = get())
        } catch (e: Exception) {
            println("jdhbd Error creating GetBookDetailsUseCaseImpl: ${e.message}")
            throw e
        }
    }

    viewModel {
        try {
            println("jdhbd Creating AuthViewModel")
            AuthViewModel(getCountryListUseCase = get(), getCountryByIpUseCase = get(), application = get())
        } catch (e: Exception) {
            println("jdhbd Error creating AuthViewModel: ${e.message}")
            throw e
        }
    }

    viewModel {
        try {
            println("jdhbd Creating BookDetailsViewModel")
            BookDetailsViewModel(getBookDetailsUseCase = get())
        } catch (e: Exception) {
            println("jdhbd Error creating BookDetailsViewModel: ${e.message}")
            throw e
        }
    }
}


package com.example.bookshelfapp.di

import IpDetailsApi
import android.annotation.SuppressLint
import com.example.bookshelfapp.data.local.BookDatabase
import com.example.bookshelfapp.data.local.DatabaseProvider
import com.example.bookshelfapp.data.repositories.AuthRepository
import com.example.bookshelfapp.data.repositories.BookDetailsRepository
import com.example.bookshelfapp.data.services.BooksApi
import com.example.bookshelfapp.data.services.CountriesApi
import com.example.bookshelfapp.domain.repositoriesimpl.AuthRepositoryImpl
import com.example.bookshelfapp.domain.repositoriesimpl.BookDetailsRepositoryImpl
import com.example.bookshelfapp.domain.usecases.BookMarkUseCase
import com.example.bookshelfapp.domain.usecases.BookMarkUseCaseImpl
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

@SuppressLint("NewApi")
val appModule = module {
    factory {
        try {
            println(" Creating Countries()")
            CountriesApi()
        } catch (e: Exception) {
            println(" Error creating CountriesApi: ${e.message}")
            throw e
        }
    }

    factory {
        try {
            println(" Creating BookList()")
            BooksApi()
        } catch (e: Exception) {
            println(" Error creating BookList(): ${e.message}")
            throw e
        }
    }
    single { IpDetailsApi }
    factory<AuthRepository> {
        try {
            println(" Creating AuthRepositoryImpl")
            AuthRepositoryImpl(countryApi = get(), ipApi = get())
        } catch (e: Exception) {
            println(" Error creating AuthRepositoryImpl: ${e.message}")
            throw e
        }
    }
    factory<BookDetailsRepository> {
        try {
            println(" Creating BookDetailsRepositoryImpl")
            BookDetailsRepositoryImpl(bookListApi = get(), context = get())
        } catch (e: Exception) {
            println(" Error creating BookDetailsRepositoryImpl: ${e.message}")
            throw e
        }
    }

    factory<GetCountryByIpUseCase> {
        try {
            println(" Creating GetCountryByIpUseCaseImpl")
            GetCountryByIpUseCaseImpl(authRepository = get())
        } catch (e: Exception) {
            println(" Error creating GetCountryByIpUseCaseImpl: ${e.message}")
            throw e
        }
    }

    factory<GetCountryListUseCase> {
        try {
            println(" Creating GetCountryListUseCaseImpl")
            GetCountryListUseCaseImpl(authRepository = get())
        } catch (e: Exception) {
            println(" Error creating GetCountryListUseCaseImpl: ${e.message}")
            throw e
        }
    }

    factory<GetBookDetailsUseCase> {
        try {
            println(" Creating GetBookDetailsUseCaseImpl")
            GetBookDetailsUseCaseImpl(bookDetailsRepository = get())
        } catch (e: Exception) {
            println(" Error creating GetBookDetailsUseCaseImpl: ${e.message}")
            throw e
        }
    }
    factory<BookMarkUseCase> {
        try {
            println(" Creating GetBookDetailsUseCaseImpl")
            BookMarkUseCaseImpl(repository = get())
        } catch (e: Exception) {
            println(" Error creating GetBookDetailsUseCaseImpl: ${e.message}")
            throw e
        }
    }

    viewModel {
        try {
            println(" Creating AuthViewModel")
            AuthViewModel(getCountryListUseCase = get(), getCountryByIpUseCase = get(), application = get())
        } catch (e: Exception) {
            println(" Error creating AuthViewModel: ${e.message}")
            throw e
        }
    }

    viewModel {
        try {
            println(" Creating BookDetailsViewModel")
            BookDetailsViewModel(getBookDetailsUseCase = get(), bookMarkUseCase = get())
        } catch (e: Exception) {
            println(" Error creating BookDetailsViewModel: ${e.message}")
            throw e
        }
    }
    single { DatabaseProvider.getDatabase(get()).bookDao() }
    single {
        get<BookDatabase>().bookDao()
    }
}


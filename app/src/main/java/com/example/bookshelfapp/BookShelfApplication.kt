package com.example.bookshelfapp

import android.app.Application
import com.example.bookshelfapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookShelfApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BookShelfApplication)
            modules(appModule)
            printLogger() // Enable Koin logging
        }
    }
}
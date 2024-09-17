package com.example.bookshelfapp

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.bookshelfapp.presentation.composables.fragments.AuthenticationFragment

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val authenticationFragment = AuthenticationFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, authenticationFragment)
            .commit()
        enableEdgeToEdge()
    }
}
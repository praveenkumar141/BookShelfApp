package com.example.bookshelfapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelfapp.presentation.composables.Navigation
import com.example.bookshelfapp.presentation.composables.fragments.AuthenticationFragment
import com.example.bookshelfapp.presentation.composables.screens.SignUpScreen
import com.example.bookshelfapp.ui.theme.BookShelfAppTheme
import org.koin.compose.KoinContext

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Set the content view for the activity
        val authenticationFragment = AuthenticationFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, authenticationFragment)  // Ensure 'fragment_container' matches your layout's container ID
            .commit()
        enableEdgeToEdge()
    }
}
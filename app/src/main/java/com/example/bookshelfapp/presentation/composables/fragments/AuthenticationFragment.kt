package com.example.bookshelfapp.presentation.composables.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.bookshelfapp.R
import com.example.bookshelfapp.presentation.composables.Navigation
import org.koin.compose.KoinContext

class AuthenticationFragment: Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                KoinContext {
                    var isAuthenticated by remember { mutableStateOf(false) }  // State to track if user is authenticated
                    println("isAuthenticated -- $isAuthenticated")
                    LaunchedEffect(isAuthenticated) {
                        if (isAuthenticated) {
                            // Navigate to BookDetailsFragment when authenticated
                            navigateToBookDetailsFragment()
                        }
                    }
                    Navigation(onAuthenticateSuccess = {isAuthenticated = true})
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun navigateToBookDetailsFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BookDetailsFragment())  // Ensure the container ID is correct
            .addToBackStack(null)  // Optionally add to backstack if you want to allow back navigation
            .commit()
    }
}
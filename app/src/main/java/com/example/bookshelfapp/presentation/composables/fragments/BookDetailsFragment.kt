package com.example.bookshelfapp.presentation.composables.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.bookshelfapp.presentation.composables.BookListHeader
import com.example.bookshelfapp.presentation.composables.screens.BookListScreen
import kotlinx.coroutines.delay
import org.koin.compose.KoinContext

class BookDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                KoinContext {
                    var onLogout by remember { mutableStateOf(false) }

                    // Use LaunchedEffect to trigger the fragment removal on logout
                    LaunchedEffect(onLogout) {
                        if (onLogout) {
                            // Delay to ensure smoother transition back to AuthFragment
                            delay(100)  // Add a short delay (300ms or more)
                            parentFragmentManager.popBackStack()  // Remove the fragment
                        }
                    }

                    // Disable the device back button
                    BackHandler(enabled = true) {
                        // Do nothing to disable the back button
                    }

                    // Only trigger UI rendering when not logging out
                    if (!onLogout) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color.LightGray),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BookListHeader(onLogout = { onLogout = true })
                            BookListScreen()
                        }
                    }
                }
            }
        }
    }
}
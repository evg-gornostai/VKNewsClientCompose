package com.example.vknewsclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vknewsclientcompose.ui.MainScreen
import com.example.vknewsclientcompose.ui.theme.VKNewsClientComposeTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKNewsClientComposeTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

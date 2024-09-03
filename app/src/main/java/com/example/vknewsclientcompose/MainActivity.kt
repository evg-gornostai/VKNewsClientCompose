package com.example.vknewsclientcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vknewsclientcompose.ui.MainScreen
import com.example.vknewsclientcompose.ui.theme.VKNewsClientComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKNewsClientComposeTheme {
                MainScreen()
            }
        }
    }
}

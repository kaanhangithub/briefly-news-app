package com.codescala.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.codescala.newsapp.presentation.onboarding.OnboardingScreen
import com.codescala.newsapp.presentation.onboarding.OnboardingViewModel
import com.codescala.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            NewsAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: OnboardingViewModel = hiltViewModel()
                    OnboardingScreen(
                        event = { event ->
                            viewModel.onEvent(event)
                        }
                    )
                }
            }
        }
    }
}

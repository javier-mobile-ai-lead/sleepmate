package com.sleepmate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import com.sleepmate.app.ui.navigation.SleepMateNavigation
import com.sleepmate.app.ui.theme.SleepMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val isDarkTheme by mainViewModel.isDarkTheme.collectAsState()
            CompositionLocalProvider(
                LocalDarkTheme provides isDarkTheme,
                LocalSetDarkTheme provides { enabled -> mainViewModel.setDarkTheme(enabled) }
            ) {
                SleepMateTheme(darkTheme = isDarkTheme) {
                    SleepMateNavigation()
                }
            }
        }
    }
}

val LocalDarkTheme = compositionLocalOf { false }
val LocalSetDarkTheme = compositionLocalOf<(Boolean) -> Unit> { {} }
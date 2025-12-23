package com.sleepmate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import com.google.android.gms.ads.MobileAds
import com.sleepmate.app.ui.navigation.SleepMateNavigation
import com.sleepmate.app.ui.theme.SleepMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize AdMob
        MobileAds.initialize(this)

        enableEdgeToEdge()

        // Get the destination route from the intent that started the activity
        val destinationRoute = intent.getStringExtra("destination_route")

        setContent {
            // 1. Read the nullable theme setting from the ViewModel
            val isDarkThemeSetting by mainViewModel.isDarkTheme.collectAsState()

            // 2. Decide which theme to use
            val useDarkTheme = isDarkThemeSetting ?: isSystemInDarkTheme()

            CompositionLocalProvider(
                // Provide the final calculated theme value
                LocalDarkTheme provides useDarkTheme,
                // This remains the same, it just sets the preference for the future
                LocalSetDarkTheme provides { enabled -> mainViewModel.setDarkTheme(enabled) }
            ) {
                 // 3. Apply the final theme to the whole app
                SleepMateTheme(darkTheme = useDarkTheme) {
                    // Pass the destination route to your navigation composable
                    SleepMateNavigation(destinationRoute = destinationRoute)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        // Esto fuerza la comprobación cada vez que la app se hace visible
        mainViewModel.checkNewDay()
    }
}

// The Local still holds a non-nullable Boolean, as the final decision is always true or false.
val LocalDarkTheme = compositionLocalOf { false }
val LocalSetDarkTheme = compositionLocalOf<(Boolean) -> Unit> { {} }

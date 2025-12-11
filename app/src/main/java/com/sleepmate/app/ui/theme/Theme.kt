package com.sleepmate.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Primary,          // Azul profundo lavanda
    secondary = Secondary,          // Azul celeste tenue
    tertiary = TextSecondary,       // Gris lavanda
    tertiaryContainer = CardBackgroundDark,
    background = BackgroundDark,
    surface = BackgroundDark,
    onPrimary = TextPrimaryDark,
    onSecondary = Color.Black,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    error = Error,
    onError = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryDark,              // Lavanda suave
    secondary = Secondary,          // Azul celeste tenue
    tertiary = TextSecondary,       // Gris lavanda
    tertiaryContainer = CardBackgroundLight,
    background = Color.White,
    surface = Color.White,
    onPrimary = TextPrimaryDark,
    onSecondary = Color.White,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    error = Error,
    onError = Color.White
)
@Composable
fun SleepMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
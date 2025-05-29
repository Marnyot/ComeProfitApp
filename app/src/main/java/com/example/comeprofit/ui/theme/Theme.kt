package com.example.comeprofit.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9C6644),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDCC7),
    onPrimaryContainer = Color(0xFF341100),
    secondary = Color(0xFF755846),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC7),
    onSecondaryContainer = Color(0xFF2B1708),
    tertiary = Color(0xFF5F6136),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE4E6AF),
    onTertiaryContainer = Color(0xFF1C1D00),
    background = Color(0xFF1E1B16),
    onBackground = Color(0xFFE9E1D9),
    surface = Color(0xFF1E1B16),
    onSurface = Color(0xFFE9E1D9),
    surfaceVariant = Color(0xFF4F4539),
    onSurfaceVariant = Color(0xFFD3C4B4),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF9C6644),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDCC7),
    onPrimaryContainer = Color(0xFF341100),
    secondary = Color(0xFF755846),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC7),
    onSecondaryContainer = Color(0xFF2B1708),
    tertiary = Color(0xFF5F6136),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE4E6AF),
    onTertiaryContainer = Color(0xFF1C1D00),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1E1B16),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1E1B16),
    surfaceVariant = Color(0xFFEFE0CF),
    onSurfaceVariant = Color(0xFF4F4539),
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

@Composable
fun ComeProfitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

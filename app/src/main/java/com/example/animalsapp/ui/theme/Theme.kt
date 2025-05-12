package com.example.animalsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary   = LightGreen,
    onPrimary = Color.Black,
    background = DarkGreen,
    onBackground = Color.White
)

private val LightColors = lightColorScheme(
    primary   = LightGreen,
    onPrimary = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

@Composable
fun AnimalsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme  = colors,
        typography   = AppTypography,
        content      = content
    )
}

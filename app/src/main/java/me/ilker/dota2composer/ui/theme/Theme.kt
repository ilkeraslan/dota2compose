package me.ilker.dota2composer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFD5C8C8),
    onPrimary = Color(0XFFFFFFFF),
    secondary = Color(0xAAC6EE71),
    onSecondary = Color(0xAAC6EE71)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF503939),
    onPrimary = Color(0xFF503939),
    secondary = Color(0xFF008D05),
    onSecondary = Color(0xFF008D05)
)

@Composable
fun Dota2ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

package me.ilker.dota2composer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0XFFFFFFFF),
    onPrimary = Color(0XFFFFFFFF),
    secondary = Color(0XAA90BB10),
    onSecondary = Color(0XAA90BB10)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0XFFEC5C5C),
    onPrimary = Color(0XFFEC5C5C),
    secondary = Color(0XAA90BB10),
    onSecondary = Color(0XAA90BB10)
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

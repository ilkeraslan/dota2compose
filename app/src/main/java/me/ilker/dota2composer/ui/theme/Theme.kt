package me.ilker.dota2composer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0XFFEC5C5C),
    primaryVariant = Color(0XAAEC5C5C),
    secondary = Color(0XAA90BB10),
    secondaryVariant = Color(0XFF90BB10)
)

private val LightColorPalette = lightColors(
    primary = Color(0XFFEC5C5C),
    primaryVariant = Color(0XAAEC5C5C),
    secondary = Color(0XAA90BB10),
    secondaryVariant = Color(0XFF90BB10)

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
package org.newsReader.com.ui.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Color.Blue,
    primaryVariant = Color.BlueVariant,
    secondary = Color.White
)


@Composable
fun GeneralTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colors = colors,
        typography = Typography.Typography,
        shapes = ShapeCustom.Shapes,
        content = content
    )
}
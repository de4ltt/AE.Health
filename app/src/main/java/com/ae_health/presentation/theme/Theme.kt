package com.ae_health.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    primaryContainer = DizzyBlue,
    secondary = Black,
    secondaryContainer = LightGray,
    background = White,
    surface = Transparent
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    primaryContainer = DizzyBlue,
    secondary = Black,
    secondaryContainer = LightGray,
    background = White,
    surface = Transparent

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val LocalExtendedFonts = staticCompositionLocalOf { extendedFonts }

val extendedFonts = FontFamily(
        Font(R.font.rubik_light, FontWeight.Light),
        Font(R.font.rubik_regular, FontWeight.W400),
        Font(R.font.rubik_medium, FontWeight.Medium),
        Font(R.font.rubik_semi_bold, FontWeight.SemiBold),
        Font(R.font.rubik_bold, FontWeight.Bold),
        Font(R.font.rubik_black, FontWeight.Black)
    )

@Composable
fun AEHealthTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object ExtendedTheme {
    val extendedFonts: FontFamily
        @Composable
        @ReadOnlyComposable
        get() = LocalExtendedFonts.current

    val extendedColors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LightColorScheme
}
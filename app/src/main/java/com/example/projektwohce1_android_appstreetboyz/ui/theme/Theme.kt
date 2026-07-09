package com.example.projektwohce1_android_appstreetboyz.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ClayPrimaryDark,
    onPrimary = OnClayPrimaryDark,
    primaryContainer = ClayPrimaryContainerDark,
    onPrimaryContainer = OnClayPrimaryContainerDark,
    secondary = OliveSecondaryDark,
    onSecondary = OnOliveSecondaryDark,
    secondaryContainer = OliveSecondaryContainerDark,
    onSecondaryContainer = OnOliveSecondaryContainerDark,
    tertiary = SandTertiaryDark,
    onTertiary = OnSandTertiaryDark,
    tertiaryContainer = SandTertiaryContainerDark,
    onTertiaryContainer = OnSandTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = OutlineDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark
)

private val LightColorScheme = lightColorScheme(
    primary = ClayPrimaryLight,
    onPrimary = OnClayPrimaryLight,
    primaryContainer = ClayPrimaryContainerLight,
    onPrimaryContainer = OnClayPrimaryContainerLight,
    secondary = OliveSecondaryLight,
    onSecondary = OnOliveSecondaryLight,
    secondaryContainer = OliveSecondaryContainerLight,
    onSecondaryContainer = OnOliveSecondaryContainerLight,
    tertiary = SandTertiaryLight,
    onTertiary = OnSandTertiaryLight,
    tertiaryContainer = SandTertiaryContainerLight,
    onTertiaryContainer = OnSandTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight
)

@Composable
fun ProjektWohce1_Android_AppStreetBoyzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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

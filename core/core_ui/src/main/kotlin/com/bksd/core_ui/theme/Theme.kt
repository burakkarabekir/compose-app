package com.bksd.core_ui.theme

import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView

internal val LocalAppColorScheme = staticCompositionLocalOf<AppColorScheme> {
    error("No ColorScheme provided")
}

internal val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No Typography provided")
}

internal val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("No Shape provided")
}

internal val LocalAppDimensions = staticCompositionLocalOf<AppDimensions> {
    error("No Dimension provided")
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = AppShapes

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = AppDimensions
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    values: List<ProvidedValue<*>> = emptyList(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        view.isForceDarkAllowed = false
    }

    val providedValues = arrayListOf<ProvidedValue<*>>()
    providedValues += LocalAppColorScheme provides colorScheme
    providedValues += LocalAppTypography provides AppTypography()
    providedValues += LocalAppShapes provides AppShapes
    providedValues += LocalAppDimensions provides AppDimensions
    providedValues += LocalIndication provides ripple()
    providedValues += LocalRippleConfiguration provides RippleConfiguration()
    providedValues.addAll(values)

    CompositionLocalProvider(
        values = providedValues.toTypedArray()
    ) {
        content()
    }
}
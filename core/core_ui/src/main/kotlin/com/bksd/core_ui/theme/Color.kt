package com.bksd.core_ui.theme

import androidx.compose.ui.graphics.Color

internal object AppColor {
    //primary
    val ShadowPrimary = Color(0xFF1E2225)
    val DarkPrimary = Color(0xFF162945)
    val Primary = Color(0xFF243A5E)
    val MidPrimary = Color(0xFF5C729E)
    val LightPrimary = Color(0xFFB7C0D7)
    val BrightPrimary = Color(0xFFE3E7EF)

    //secondary
    val ShadowSecondary = Color(0xFF2E0109)
    val DarkSecondary = Color(0xFF218A66)
    val Secondary = Color(0xFF00C48C)
    val MidSecondary = Color(0xFF7ADAB5)
    val LightSecondary = Color(0xFFB2E9D3)
    val BrightSecondary = Color(0xFFE6FBF3)

    //neutrals
    val Shadow = Color(0xFF131010)
    val DarkGray = Color(0xFF1E2225)
    val MidGray = Color(0xFF5D6369)
    val Gray = Color(0xFFA2A9B0)
    val LightGray = Color(0xFFC2C7CC)
    val BrightGray = Color(0xFFF4F6F8)
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    //success
    val Green = Color(0xFF46A916)
    val BrightGreen = Color(0xFFEDF6E8)

    //warning
    val Yellow = Color(0xFFFFDD00)
    val BrightYellow = Color(0xFFFFF8CC)

    //error
    val Orange = Color(0xFFF95E20)
    val DarkOrange = Color(0xFF993100)
    val BrightOrange = Color(0xFFFFDCCC)

    //others
    val Background = Color(0xFFF7F7F7)
}

data class AppColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val success: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarningContainer: Color
)
package com.bksd.core_ui.theme

val LightColorScheme = AppColorScheme(
    primary = AppColor.Primary,
    onPrimary = AppColor.White,
    primaryContainer = AppColor.BrightPrimary,
    onPrimaryContainer = AppColor.DarkPrimary,
    secondary = AppColor.Secondary,
    onSecondary = AppColor.White,
    tertiary = AppColor.MidPrimary,
    onTertiary = AppColor.White,
    background = AppColor.Background,
    onBackground = AppColor.DarkGray,
    surface = AppColor.White,
    onSurface = AppColor.DarkGray,
    onSurfaceVariant = AppColor.MidGray,
    outline = AppColor.LightGray,
    error = AppColor.Orange,
    onError = AppColor.White,
    errorContainer = AppColor.BrightOrange,
    onErrorContainer = AppColor.DarkOrange,
    success = AppColor.Green,
    onSuccessContainer = AppColor.BrightGreen,
    warning = AppColor.Yellow,
    onWarningContainer = AppColor.BrightYellow
)

/**
 * The definition of colors for the Dark Theme.
 * To achieve polish and high contrast in dark mode, we often use LIGHTER shades
 * for primary actions and DARKER shades for text on those actions.
 */
val DarkColorScheme = AppColorScheme(
    primary = AppColor.LightPrimary,
    onPrimary = AppColor.DarkPrimary,
    primaryContainer = AppColor.Primary,
    onPrimaryContainer = AppColor.BrightPrimary,
    secondary = AppColor.LightSecondary,
    onSecondary = AppColor.DarkSecondary,
    tertiary = AppColor.MidPrimary,
    onTertiary = AppColor.BrightPrimary,
    background = AppColor.DarkGray,
    onBackground = AppColor.BrightGray,
    surface = AppColor.ShadowPrimary,
    onSurface = AppColor.LightGray,
    onSurfaceVariant = AppColor.Gray,
    outline = AppColor.MidGray,
    error = AppColor.Orange,
    onError = AppColor.White,
    errorContainer = AppColor.DarkOrange,
    onErrorContainer = AppColor.BrightOrange,
    success = AppColor.Green,
    onSuccessContainer = AppColor.BrightGreen,
    warning = AppColor.Yellow,
    onWarningContainer = AppColor.BrightYellow
)
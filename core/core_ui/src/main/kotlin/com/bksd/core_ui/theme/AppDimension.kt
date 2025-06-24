package com.bksd.core_ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Stable
object AppDimensions {
    // Spacing
    val spacingNone = 0.dp
    val spacingExtraSmall = 4.dp
    val spacingSmall = 8.dp
    val spacingMedium = 16.dp
    val spacingLarge = 24.dp
    val spacingExtraLarge = 32.dp
    val spacingHuge = 48.dp
    val spacingExtraHuge = 64.dp

    // Sizes
    val iconSmall = 16.dp
    val iconMedium = 24.dp
    val iconLarge = 32.dp
    val iconExtraLarge = 48.dp

    // Elevation
    val elevationNone = 0.dp
    val elevationSmall = 2.dp
    val elevationMedium = 4.dp
    val elevationLarge = 8.dp

    // Padding
    val paddingExtraSmall = PaddingValues(spacingExtraSmall)
    val paddingSmall = PaddingValues(spacingSmall)
    val paddingMedium = PaddingValues(spacingMedium)
    val paddingLarge = PaddingValues(spacingLarge)

    // Component sizes
    val buttonHeight = 48.dp
    val inputFieldHeight = 56.dp
    val smallButtonHeight = 32.dp
    val iconButtonSize = 40.dp
    val fabSize = 56.dp
    val smallFabSize = 40.dp
    val dividerThickness = 1.dp
    val borderWidth = 1.dp
    val borderWidthFocused = 2.dp

    // Corner radius
    val radiusSmall = 4.dp
    val radiusMedium = 8.dp
    val radiusLarge = 12.dp
    val radiusExtraLarge = 16.dp
    val radiusFull = 50.dp

    // Animation
    val animationDurationShort = 150
    val animationDurationMedium = 300
    val animationDurationLong = 500
}

// Extension functions for easier dimension access
fun Modifier.smallSpacing() = this.padding(AppDimensions.spacingSmall)
fun Modifier.mediumSpacing() = this.padding(AppDimensions.spacingMedium)
fun Modifier.largeSpacing() = this.padding(AppDimensions.spacingLarge)

fun Modifier.smallWidth() = this.width(AppDimensions.spacingSmall)
fun Modifier.mediumWidth() = this.width(AppDimensions.spacingMedium)
fun Modifier.largeWidth() = this.width(AppDimensions.spacingLarge)

fun Modifier.smallHeight() = this.height(AppDimensions.spacingSmall)
fun Modifier.mediumHeight() = this.height(AppDimensions.spacingMedium)
fun Modifier.largeHeight() = this.height(AppDimensions.spacingLarge)

package com.bksd.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.dp

@Stable
object AppShapes {
    // Small components (buttons, chips)
    val small = RoundedCornerShape(4.dp)

    // Medium components (cards, dialogs)
    val medium = RoundedCornerShape(8.dp)

    // Large components (modals, sheets)
    val large = RoundedCornerShape(16.dp)

    // Full rounded (e.g., pills, FABs)
    val full = RoundedCornerShape(percent = 50)
}
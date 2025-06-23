package com.bksd.word_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingOverlay(
    overlayColor: Color = Color.Black.copy(alpha = 0.3f),
    indicator: @Composable () -> Unit = { CircularProgressIndicator() },
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(overlayColor),
            contentAlignment = Alignment.Center
        ) {
            indicator()
        }
    }
}
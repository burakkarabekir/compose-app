package com.bksd.composeapp.route.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.core_ui.extension.clickableWithoutRipple
import com.bksd.core_ui.theme.AppDimensions.spacingExtraSmall
import com.bksd.core_ui.theme.AppTheme
import com.bksd.core_ui.theme.largeSpacing

@Composable
fun CustomNavigationBarItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacingExtraSmall),
        modifier = modifier
            .clickableWithoutRipple { onClick() }
            .padding(vertical = 9.dp)
    ) {
        icon.invoke()
        label.invoke()
        if (selected) {
            Box(
                modifier = Modifier
                    .size(spacingExtraSmall)
                    .clip(CircleShape)
                    .background(AppTheme.colorScheme.primary)
            )
        }
    }
}

@Preview(showBackground = true, name = "Custom Nav Item - Selected")
@Composable
fun PreviewSelectedCustomNavigationBarItem() {
    AppTheme {
        Surface(color = AppTheme.colorScheme.surface) {
            CustomNavigationBarItem(
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Home Icon",
                        modifier = Modifier.largeSpacing(),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        "Home",
                    )
                },
                selected = true,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Custom Nav Item - Unselected")
@Composable
fun PreviewUnselectedCustomNavigationBarItem() {
    AppTheme {
        Surface(color = AppTheme.colorScheme.surface) {
            CustomNavigationBarItem(
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home Icon",
                        modifier = Modifier.largeSpacing(),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    Text(
                        "Home",
                        style = AppTheme.typography.bottomMenuText.copy(AppTheme.colorScheme.onSurface)
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }
}
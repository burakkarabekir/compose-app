package com.bksd.feature_home.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A Bottom Navigation bar for the WordUp app.
 * Shows Home, Favorites, Quiz, and Search items.
 * @param selectedItem currently selected BottomNavItem
 * @param onItemSelected callback when an item is tapped
 */
@Composable
fun AppBottomNavigation(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar {
        BottomNavItem.entries.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = item == selectedItem,
                onClick = { onItemSelected(item) }
            )
        }
    }
}

/**
 * Enumeration of bottom navigation items.
 */
enum class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    Home("home", Icons.Default.Home, "Home"),
    Search("search", Icons.Default.Search, "Search"),
    Favorites("favorites", Icons.Default.Favorite, "Favorites"),
    Quiz("quiz", Icons.Default.PlayArrow, "Quiz");

    companion object {
        fun values(): List<BottomNavItem> = listOf(Home, Search, Favorites, Quiz)
    }
}
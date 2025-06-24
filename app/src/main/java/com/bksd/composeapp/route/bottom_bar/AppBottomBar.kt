package com.bksd.composeapp.route.bottom_bar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bksd.core_ui.theme.AppTheme
import com.bksd.route.MainGraph
import com.bksd.route.R

private val bottomBarItems = listOf(
    BottomBarItem(
        nameResId = R.string.text_home_screen,
        route = MainGraph.HomeScreenRoute,
        unSelectedIconResId =  Icons.Outlined.Home,
        selectedIconResId =  Icons.Filled.Home
    ),
    BottomBarItem(
        nameResId = R.string.text_search_screen,
        route = MainGraph.SearchScreenRoute,
        unSelectedIconResId = Icons.Outlined.Search,
        selectedIconResId = Icons.Filled.Search
    )
)

data class BottomBarItem<T : Any>(
    @StringRes val nameResId: Int,
    val route: T,
    val unSelectedIconResId: ImageVector,
    val selectedIconResId: ImageVector
)

@Composable
fun AppBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    navigateBottomBarItem: (route: Any) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface(
        modifier = modifier,
        color = AppTheme.colorScheme.primaryContainer,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(BottomAppBarDefaults.windowInsets)
        ) {
            bottomBarItems.forEach { item ->
                val isSelected = currentDestination?.hasRoute(item.route::class) == true
                val iconResId = if (isSelected) item.selectedIconResId else item.unSelectedIconResId

                CustomNavigationBarItem(
                    icon = {
                        Icon(
                            iconResId,
                            contentDescription = stringResource(id = item.nameResId),
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                    },
                    label = {
                        val style =
                            if (isSelected) AppTheme.typography.bottomMenuSelected.copy(AppTheme.colorScheme.primary)
                            else AppTheme.typography.bottomMenuText.copy(AppTheme.colorScheme.outline)
                        Text(
                            stringResource(item.nameResId),
                            style = style
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navigateBottomBarItem.invoke(item.route)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AppTheme {
        val navController = rememberNavController()
        AppBottomBar(
            navController = navController,
            navigateBottomBarItem = {}
        )
    }
}

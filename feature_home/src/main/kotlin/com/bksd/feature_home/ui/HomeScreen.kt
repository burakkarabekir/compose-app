package com.bksd.feature_home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.core_ui.UiEvent
import com.bksd.feature_home.ui.component.AnimatedCategoryRow
import com.bksd.feature_home.ui.component.AppBottomNavigation
import com.bksd.feature_home.ui.component.BottomNavItem
import com.bksd.feature_home.ui.component.RecentCard
import com.bksd.feature_home.ui.component.WordOfDayCard
import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.feature_home.ui.model.WordOfDayUi
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: AppNavigator,
    viewModel: HomeVM = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navigator.navigate(AppNavigationCommand.Navigate(event.route))
                is UiEvent.NavigateCall -> event.route()
                else -> {}
            }
        }
    }

    HomeContent(
        onSearchClick = { viewModel.onEvent(HomeScreenEvent.OnSearch) },
        wordOfDay = uiState.getOrNull()?.wordOfDay,
        onWordOfDayClick = { viewModel.onEvent(HomeScreenEvent.OnWordOfDayClick) },
        selectedCategory = uiState.getOrNull()?.selectedCategory ?: 0,
        onCategorySelected = { viewModel.onEvent(HomeScreenEvent.OnCategorySelected(it)) },
        recentSearches = uiState.getOrNull()?.recentSearches,
        onRecentSearchClick = { viewModel.onEvent(HomeScreenEvent.OnRecentSearchClick(it)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeContent(
    wordOfDay: WordOfDayUi?,
    selectedCategory: Int,
    onWordOfDayClick: () -> Unit,
    recentSearches: List<RecentWordUi>?,
    onCategorySelected: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onRecentSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = { AppBottomNavigation(selectedItem = BottomNavItem.Home, onItemSelected = {}) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Surface(
                tonalElevation = 4.dp,
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = "",
                    onValueChange = { },
                    enabled = false,
                    readOnly = true,
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text("Search for a word") },
                    colors = TextFieldDefaults.colors(
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(onClick = onSearchClick)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Word of the Day
            WordOfDayCard(
                word = wordOfDay,
                modifier = modifier,
                onClick = { onWordOfDayClick() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Category selector with animation
            AnimatedCategoryRow(
                selectedIndex = selectedCategory,
                onSelect = onCategorySelected
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Recent Searches
            recentSearches?.let {
                RecentCard(
                    recentSearches = it,
                    onRecentSearchClick = onRecentSearchClick,
                    modifier = modifier
                )
            }
        }
    }
}
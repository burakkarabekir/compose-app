package com.bksd.feature_home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.core_ui.UiEvent
import com.bksd.core_ui.UiText
import com.bksd.core_ui.component.WordDetailCard
import com.bksd.core_ui.model.WordDetailCardUi
import com.bksd.feature_home.R
import com.bksd.feature_home.ui.component.AnimatedCategoryRow
import com.bksd.feature_home.ui.component.AppBottomNavigation
import com.bksd.feature_home.ui.component.BottomNavItem
import com.bksd.feature_home.ui.component.RecentCard
import com.bksd.feature_home.ui.model.RecentWordUi
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
    wordOfDay: WordDetailCardUi?,
    selectedCategory: Int,
    onWordOfDayClick: () -> Unit,
    recentSearches: List<RecentWordUi>?,
    onCategorySelected: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onRecentSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = UiText.StringResource(id = R.string.header_app_name).asString(),
                        modifier = modifier.padding(end = 16.dp),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = modifier
                                .padding(end = 12.dp)
                                .size(28.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = modifier,
            )
        },
        bottomBar = {
            AppBottomNavigation(selectedItem = BottomNavItem.Home, onItemSelected = {
                when (it) {
                    BottomNavItem.Search -> onSearchClick()
                    else -> {}
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Word of the Day
            wordOfDay?.let {
                WordDetailCard(
                    detailUi = it,
                    modifier = modifier,
                    onClick = { onWordOfDayClick() }
                )
            }

            Spacer(modifier = modifier.height(24.dp))

            // Category selector with animation
            AnimatedCategoryRow(
                selectedIndex = selectedCategory,
                onSelect = onCategorySelected
            )

            Spacer(modifier = modifier.height(24.dp))

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
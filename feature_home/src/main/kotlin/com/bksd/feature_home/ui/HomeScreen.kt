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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.core_ui.UiText
import com.bksd.core_ui.extension.CollectFlowAsEvent
import com.bksd.core_ui.theme.AppDimensions.spacingExtraLarge
import com.bksd.core_ui.theme.AppDimensions.spacingMedium
import com.bksd.core_ui.theme.AppTheme
import com.bksd.feature_home.R
import com.bksd.feature_home.ui.component.AnimatedCategoryRow
import com.bksd.feature_home.ui.component.RecentWordsContainer
import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.feature_home.ui.state.HomeScreenUi
import com.bksd.route.AppNavigator
import com.bksd.route.WordDetailGraph
import com.bksd.word_ui.component.LoadingOverlay
import com.bksd.word_ui.component.WordDetailCard
import com.bksd.word_ui.model.WordOfDayDetailCardUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeStore = koinViewModel(),
    navigator: AppNavigator = koinInject(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.isLoading -> {
            LoadingOverlay()
        }
        else -> {
            HomeContent(
                uiModel = uiState.uiModel,
                selectedCategory = uiState.selectedCategory,
                onEvent = viewModel::onEvent,
                modifier = Modifier
            )
        }
    }

    CollectFlowAsEvent(viewModel.uiEffect) { effect ->
        when (effect) {
            is HomeScreenEffect.NavigateToCategory -> Unit
            is HomeScreenEffect.NavigateToRecentSearchDetails -> {
                coroutineScope.launch {
                    navigator.navigate(
                        WordDetailGraph.WordDetailScreenRoute(
                            word = uiState.uiModel.recentSearches
                                ?.find { it.text == effect.queryOrId }
                                ?.text.orEmpty()
                        )
                    )
                }
            }

            is HomeScreenEffect.NavigateToWordDetails -> {
                coroutineScope.launch {
                    navigator.navigate(
                        WordDetailGraph.WordDetailScreenRoute(
                            word = uiState.uiModel.wordOfDay?.word.orEmpty()
                        )
                    )
                }
            }

            is HomeScreenEffect.ShowMessage -> Unit
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeContent(
    uiModel: HomeScreenUi,
    selectedCategory: Int,
    modifier: Modifier = Modifier,
    onEvent: (HomeScreenEvent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = UiText.StringResource(id = R.string.header_app_name).asString(),
                        modifier = modifier.padding(end = spacingMedium),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = AppTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = UiText.StringResource(id = R.string.settings)
                                .asString(),
                            modifier = modifier
                                .padding(end = 12.dp)
                                .size(28.dp),
                            tint = AppTheme.colorScheme.primary
                        )
                    }
                },
                modifier = modifier,
            )
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
            uiModel.wordOfDay?.let {
                WordDetailCard(
                    detailUi = it,
                    modifier = modifier,
                    onClick = { onEvent(HomeScreenEvent.OnWordOfDayClick(it.word.orEmpty())) }
                )
            }

            Spacer(modifier = modifier.height(24.dp))

            // Category selector with animation
            AnimatedCategoryRow(
                selectedIndex = selectedCategory,
                onSelect = { index -> onEvent(HomeScreenEvent.OnCategorySelected(index)) }
            )

            Spacer(modifier = modifier.height(spacingExtraLarge))

            // Recent Searches
            uiModel.recentSearches?.let { recentSearches ->
                RecentWordsContainer(
                    recentSearches = recentSearches,
                    onRecentSearchClick = { word -> onEvent(HomeScreenEvent.OnRecentSearchClick(word)) },
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Home Content Preview")
@Composable
fun HomeContentPreview() {
    AppTheme {
        HomeContent(
            uiModel = HomeScreenUi(
                wordOfDay = WordOfDayDetailCardUi(
                    word = "Test",
                    definition = "",
                    synonyms = listOf(),
                    antonyms = listOf(),
                    isFavorite = false,
                    label = "Word Of The Day"

                ),
                recentSearches = listOf(
                    RecentWordUi("Compose"),
                    RecentWordUi("Compose"),
                    RecentWordUi("Compose"),
                )
            ),
            selectedCategory = 0,
        )
    }
}
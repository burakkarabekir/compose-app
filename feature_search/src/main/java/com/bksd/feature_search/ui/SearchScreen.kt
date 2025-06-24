@file:OptIn(ExperimentalMaterial3Api::class)

package com.bksd.feature_search.ui

import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.core_ui.UiText
import com.bksd.core_ui.extension.CollectFlowAsEvent
import com.bksd.core_ui.theme.AppTheme
import com.bksd.feature_search.R
import com.bksd.feature_search.ui.event.SearchScreenEvent
import com.bksd.feature_search.ui.state.SearchScreenUi
import com.bksd.route.AppNavigator
import com.bksd.route.WordDetailGraph
import com.bksd.word_ui.component.LoadingOverlay
import com.bksd.word_ui.component.WordDetailCard
import com.bksd.word_ui.model.WordDetailCardUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun SearchScreen(
    viewModel: SearchStore = koinViewModel(),
    navigator: AppNavigator = koinInject(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.isLoading -> {
            LoadingOverlay()
        }
        else -> {
            SearchContent(
                uiModel = uiState.uiModel,
                onEvent = viewModel::onEvent,
                modifier = Modifier
            )
        }
    }

    CollectFlowAsEvent(viewModel.uiEffect) { effect ->
        when (effect) {
            is SearchScreenEffect.AddToFavorite -> Toast.makeText(
                context,
                "AddToFavorite ::Added to favorite",
                Toast.LENGTH_SHORT
            ).show()

            is SearchScreenEffect.NavigateToWordDetail -> coroutineScope.launch {
                navigator.navigate(
                    WordDetailGraph.WordDetailScreenRoute(word = uiState.uiModel.word?.word.orEmpty())
                )
            }

            is SearchScreenEffect.ShowMessage -> Toast.makeText(
                context,
                "ShowMessage :: Added to favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    uiModel: SearchScreenUi,
    onEvent: (SearchScreenEvent) -> Unit = {},
) {
    val searchQuery = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(200)
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = UiText.StringResource(id = R.string.search_header).asString(),
                        modifier = modifier.padding(end = 16.dp),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = AppTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Surface(
                tonalElevation = 4.dp,
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text("Search for a word") },
                    colors = TextFieldDefaults.colors(
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    modifier = modifier
                        .fillMaxSize()
                        .focusRequester(focusRequester).focusable(uiModel.word != null),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            onEvent(SearchScreenEvent.OnSearch(searchQuery.value))
                        }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    )
                )
            }
            uiModel.word?.let {
                Spacer(modifier = modifier.height(24.dp))
                WordDetailCard(detailUi = it) {
                    onEvent(SearchScreenEvent.OnWordClick(it.word))
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Search Content Preview")
@Composable
fun HomeContentPreview() {
    AppTheme {
        SearchContent(
            uiModel = SearchScreenUi(
                WordDetailCardUi(
                    word = "Test",
                    definition = "Test",
                    synonyms = listOf(),
                    antonyms = listOf(),
                    isFavorite = false
            )
        )
        )
    }
}
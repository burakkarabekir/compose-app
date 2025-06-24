package com.bksd.feature_home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.core_ui.extension.CollectFlowAsEvent
import com.bksd.core_ui.theme.AppDimensions.paddingExtraSmall
import com.bksd.core_ui.theme.AppTheme
import com.bksd.feature_home.ui.component.WordSection
import com.bksd.feature_home.ui.state.WordDetailScreenEvent
import com.bksd.route.AppNavigator
import com.bksd.word_ui.component.LoadingOverlay
import com.bksd.word_ui.model.WordDetailCardUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDetailScreen(
    navigator: AppNavigator = koinInject(),
    viewModel: WordDetailStore = koinViewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.isLoading -> {
            LoadingOverlay()
        }
        else -> {
            WordDetailContent(
                uiModel = uiState.uiModel as WordDetailCardUi,
                onEvent = viewModel::onEvent,
                modifier = Modifier
            )
        }
    }

    CollectFlowAsEvent(viewModel.uiEffect) { effect ->
        when (effect) {
            is WordDetailScreenEffect.PlayAudio -> {
                Toast.makeText(context, "Clicked onPlayAudio", Toast.LENGTH_SHORT).show()
            }

            WordDetailScreenEffect.NavigateBack -> coroutineScope.launch { navigator.navigateBack() }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDetailContent(
    uiModel: WordDetailCardUi,
    modifier: Modifier = Modifier,
    onEvent: (WordDetailScreenEvent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onEvent(WordDetailScreenEvent.OnBackClicked) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = {
                onEvent(
                    WordDetailScreenEvent.OnFavoriteClicked(
                        uiModel.word,
                        uiModel.isFavorite
                    )
                )
            }) {
                Icon(
                    imageVector = if (uiModel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = uiModel.word,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Play pronunciation")
                }
                Text(
                    text = uiModel.word,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )

            }

            Spacer(modifier = modifier.height(24.dp))
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (uiModel.definition.isNullOrEmpty().not()) {
                    WordSection(
                        title = "Definition",
                        content = uiModel.definition.orEmpty(),
                        modifier = modifier
                    )
                }
                uiModel.synonyms?.let { synonymList ->
                    if (synonymList.isNotEmpty()) {
                        Column {
                            Text(
                                text = "Synonyms",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = modifier.padding(paddingExtraSmall)
                            )
                        }
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            items(synonymList) { synonym ->
                                Text(
                                    text = synonym,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    modifier = modifier.padding(paddingExtraSmall)
                                )
                            }
                        }
                    }
                }
                uiModel.antonyms?.let { antonymList ->
                    if (antonymList.isNotEmpty()) {
                        Column {
                            Text(
                                text = "Antonyms",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = modifier.padding(paddingExtraSmall)
                            )
                        }
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            items(antonymList) { antonym ->
                                Text(
                                    text = antonym,
                                    style = AppTheme.typography.body1,
                                    color = AppTheme.colorScheme.onBackground,
                                    modifier = modifier.padding(paddingExtraSmall)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordDetailContentPreview() {
    // Assuming you have a custom theme. Replace YourAppTheme with your actual theme.
    // If not, you can use MaterialTheme {} directly.
    AppTheme {
        WordDetailContent(
            uiModel = WordDetailCardUi(
                word = "Compose",
                definition = "UI toolkit for building native Android UI.",
                synonyms = listOf("Android", "UI", "Widget"),
                antonyms = listOf("Material", "Jetpack")
            )
        )
    }
}
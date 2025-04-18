package com.bksd.feature_home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.feature_home.ui.state.HomeScreenState
import com.bksd.route.AppNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: AppNavigator,
    viewModel: HomeVM = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    HomeContent(
        state = uiState,
        onEvent = viewModel::onEvent  // Handles HomeEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(state: UiState<HomeScreenState>, onEvent: (HomeScreenEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    IconButton(
                        onClick = {
                            /*navigator.navigate(
                                NavigationType.Navigate(AppRoutes.Settings.route)
                            )*/
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Text(text = "Home Screen", modifier = Modifier.padding(paddingValues))
    }
}
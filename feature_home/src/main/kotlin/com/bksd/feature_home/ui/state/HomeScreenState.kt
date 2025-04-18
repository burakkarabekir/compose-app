package com.bksd.feature_home.ui.state

data class HomeScreenState(
    val items: List<String> = emptyList(),
    val isRefreshing: Boolean = false,
)

package com.bksd.feature_home.ui.state

import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.feature_home.ui.model.WordOfDayUi

data class HomeScreenState(
    val recentSearches: List<RecentWordUi>? = null,
    val selectedCategory: Int = 0,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val wordOfDay: WordOfDayUi? = null,
)

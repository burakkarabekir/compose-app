package com.bksd.feature_home.ui.state

import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.word_ui.model.WordDetailUi

data class HomeScreenState(
    val uiModel: HomeScreenUi = HomeScreenUi(),
    val selectedCategory: Int = 0,
    val isLoading: Boolean = false
)

data class HomeScreenUi(
    val wordOfDay: WordDetailUi? = null,
    val recentSearches: List<RecentWordUi>? = null,
)
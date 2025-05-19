package com.bksd.feature_home.ui.state

import com.bksd.feature_home.ui.model.RecentWordUi
import com.bksd.word_ui.model.WordDetailUi

data class HomeScreenState(
    val recentSearches: List<RecentWordUi>? = null,
    val selectedCategory: Int = 0,
    val isLoading: Boolean = false,
    val wordOfDay: WordDetailUi? = null,
) {
    fun showWoDCard(): Boolean = wordOfDay != null
}

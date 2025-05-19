package com.bksd.feature_home.ui.state

import com.bksd.core_ui.model.WordDetailUi
import com.bksd.feature_home.ui.model.RecentWordUi

data class HomeScreenState(
    val recentSearches: List<RecentWordUi>? = null,
    val selectedCategory: Int = 0,
    val isLoading: Boolean = false,
    val wordOfDay: WordDetailUi? = null,
) {
    fun showWoDCard(): Boolean = wordOfDay != null
}

package com.bksd.feature_search.ui.state

import com.bksd.core_ui.model.WordDetailCardUi

data class SearchScreenState(
    val searchQuery: String = "",
    val word: WordDetailCardUi
)

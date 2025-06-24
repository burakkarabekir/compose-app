package com.bksd.feature_search.ui.state

import com.bksd.word_ui.model.WordDetailCardUi

data class SearchScreenState(
    val uiModel: SearchScreenUi = SearchScreenUi(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
)
data class SearchScreenUi(
    val word: WordDetailCardUi? = null
)

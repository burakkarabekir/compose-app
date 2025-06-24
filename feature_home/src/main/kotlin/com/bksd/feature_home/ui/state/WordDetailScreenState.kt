package com.bksd.feature_home.ui.state

import com.bksd.word_ui.model.WordDetailCardUi
import com.bksd.word_ui.model.WordDetailUi

data class WordDetailScreenState(
    val uiModel: WordDetailUi = WordDetailCardUi(),
    val isLoading: Boolean = false
)
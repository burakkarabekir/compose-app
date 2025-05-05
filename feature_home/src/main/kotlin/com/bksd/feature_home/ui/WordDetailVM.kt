package com.bksd.feature_home.ui

import androidx.lifecycle.SavedStateHandle
import com.bksd.core_ui.BaseVM
import com.bksd.core_ui.UiEvent
import com.bksd.core_ui.UiState
import com.bksd.feature_home.ui.state.WordDetailScreenEvent
import com.bksd.feature_home.ui.state.WordDetailScreenState

class WordDetailVM(
    savedStateHandle: SavedStateHandle
) : BaseVM<WordDetailScreenState, WordDetailScreenEvent>(
    initialState = UiState.Success<WordDetailScreenState>(
        WordDetailScreenState(
            word = savedStateHandle["word"] ?: "",
            definition = savedStateHandle["definition"] ?: "",
            synonyms = savedStateHandle.get<List<String>>("synonyms") ?: emptyList(),
            antonyms = savedStateHandle.get<List<String>>("antonyms") ?: emptyList(),
            examples = savedStateHandle.get<List<String>>("examples") ?: emptyList(),
            pronunciation = savedStateHandle["pronunciation"] ?: ""
        )
    )
) {

    override fun onEvent(event: WordDetailScreenEvent) {
        when (event) {
            is WordDetailScreenEvent.OnBackClicked -> sendEvent(UiEvent.NavigateBack)
            is WordDetailScreenEvent.OnFavoriteClicked -> Unit
            is WordDetailScreenEvent.OnPlayPronunciationClicked -> Unit
        }
    }
}
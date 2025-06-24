package com.bksd.feature_home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_ui.BaseStore
import com.bksd.core_ui.extension.simpleLaunch
import com.bksd.feature_home.domain.usecase.GetWordDetailUseCase
import com.bksd.feature_home.domain.usecase.SetWordFavoriteUseCase
import com.bksd.feature_home.mapper.WordDetailMapper
import com.bksd.feature_home.ui.state.WordDetailScreenEvent
import com.bksd.feature_home.ui.state.WordDetailScreenEvent.OnBackClicked
import com.bksd.feature_home.ui.state.WordDetailScreenEvent.OnFavoriteClicked
import com.bksd.feature_home.ui.state.WordDetailScreenEvent.OnPlayPronunciationClicked
import com.bksd.feature_home.ui.state.WordDetailScreenState
import com.bksd.route.WordDetailGraph
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class WordDetailStore(
    private val getWordDetail: GetWordDetailUseCase,
    private val setWordDetail: SetWordFavoriteUseCase,
    private val wordDetailMapper: WordDetailMapper,
    savedStateHandle: SavedStateHandle
) : BaseStore<WordDetailScreenState, WordDetailScreenEvent, WordDetailScreenEffect>() {
    private val args: WordDetailGraph.WordDetailScreenRoute = savedStateHandle.toRoute()

    private val _uiState = MutableStateFlow(WordDetailScreenState())
    override val uiState = _uiState
        .onStart { loadWordDetail() }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            WordDetailScreenState()
        )

    private val _uiEffect = Channel<WordDetailScreenEffect>(Channel.BUFFERED)
    override val uiEffect = _uiEffect.receiveAsFlow()

    override fun onEvent(event: WordDetailScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is OnBackClicked -> _uiEffect.send(WordDetailScreenEffect.NavigateBack)
                is OnFavoriteClicked -> {
                    _uiState.updateAndGet { currentState ->
                        currentState.copy(uiModel = currentState.uiModel.setFavorite(!event.isFavorite))
                    }.also {
                        setFavoriteStatus(event.id, event.isFavorite)
                    }
                }

                is OnPlayPronunciationClicked -> _uiEffect.send(
                    WordDetailScreenEffect.PlayAudio(
                        event.id
                    )
                )
            }
        }
    }

    private fun loadWordDetail(word: String = args.word) = simpleLaunch {
        getWordDetail(word)
            .collect { state ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = state is DomainResult.Loading
                    )
                }
                when (state) {
                    DomainResult.Empty -> Unit
                    is DomainResult.Error -> Unit
                    DomainResult.Loading -> {}
                    is DomainResult.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                uiModel = wordDetailMapper.map(state.data),
                            )
                        }
                    }
                }
            }
    }

    fun setFavoriteStatus(word: String, isFavorite: Boolean) = simpleLaunch {
        setWordDetail(Pair(word, !isFavorite))
    }
}
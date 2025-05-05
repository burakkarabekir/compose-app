package com.bksd.core_ui

import androidx.lifecycle.ViewModel
import com.bksd.core_ui.extension.simpleLaunch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

/**
 * Base ViewModel class that provides common functionality for managing UI state and events.
 *
 * @param S The type of data associated with the [UiState.Success] state.
 * @param E The type of events that the ViewModel can handle.
 */
abstract class BaseVM<S, E>(initialState: UiState<S>) : ViewModel() {
    protected val _uiState = MutableStateFlow<UiState<S>>(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /**
     * Called to handle an incoming UI event.
     *
     * @param event The event to handle.
     */
    abstract fun onEvent(event: E)

    protected fun updateState(transform: (UiState<S>) -> UiState<S>) = _uiState.update(transform)
    protected fun sendEvent(event: UiEvent) = simpleLaunch { _uiEvent.send(event) }
}
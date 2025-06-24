package com.bksd.core_ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Base Store class that provides common functionality for managing UI state and effects.
 *
 * @param S The type of data associated with the [UiState.Success] state.
 * @param E The type of events that the ViewModel can handle.
 * @param SE The type of effects that the user can handle.
 */
abstract class BaseStore<S, E, SE> : ViewModel() {
    abstract val uiState : StateFlow<S>
    abstract val uiEffect : Flow<SE>
    abstract fun onEvent(event: E)
}
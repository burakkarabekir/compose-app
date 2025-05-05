package com.bksd.core_ui

sealed interface UiEvent {
    data class ShowMessage(val message: String) : UiEvent
    data class Navigate(val route: String) : UiEvent
    data class NavigateCall(val route: () -> Unit) : UiEvent
    data object NavigateUp : UiEvent
    data object NavigateBack : UiEvent
}
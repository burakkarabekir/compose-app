package com.bksd.feature_home.ui

import com.bksd.core_ui.BaseVM
import com.bksd.core_ui.UiEvent
import com.bksd.feature_home.ui.state.HomeScreenEvent
import com.bksd.feature_home.ui.state.HomeScreenState

class HomeVM(
) : BaseVM<HomeScreenState, HomeScreenEvent>() {

    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ItemClicked -> sendEvent(UiEvent.Navigate(event.id))
            HomeScreenEvent.RefreshRequested -> sendEvent(UiEvent.NavigateBack)
        }
    }
}
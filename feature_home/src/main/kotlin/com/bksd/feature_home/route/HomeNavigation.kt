package com.bksd.feature_home.route

import com.bksd.core_ui.model.WordDetailCardUi
import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

interface HomeNavigation {
    fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailCardUi)
    fun navigateToSearch()
}

class HomeNavigationImpl(
    private val navigator: AppNavigator
) : HomeNavigation {

    override fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailCardUi) {
        navigator.navigate(
            AppNavigationCommand.Navigate(
                AppRoutes.WordDetail.withArgs(wordOfDayUi.word, wordOfDayUi.pronunciation.orEmpty())
            )
        )
    }

    override fun navigateToSearch() {
        navigator.navigate(
            AppNavigationCommand.Navigate(AppRoutes.Search.route)
        )
    }
}
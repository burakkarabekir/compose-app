package com.bksd.feature_home.route

import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes
import com.bksd.word_ui.model.WordDetailUi

interface HomeNavigation {
    fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailUi)
    fun navigateToSearch()
}

class HomeNavigationImpl(
    private val navigator: AppNavigator
) : HomeNavigation {

    override fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailUi) {
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
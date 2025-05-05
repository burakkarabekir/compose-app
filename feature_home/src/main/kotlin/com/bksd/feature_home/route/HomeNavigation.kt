package com.bksd.feature_home.route

import com.bksd.feature_home.ui.model.WordOfDayUi
import com.bksd.route.AppNavigationCommand
import com.bksd.route.AppNavigator
import com.bksd.route.AppRoutes

interface HomeNavigation {
    fun navigateToWordOfDayDetail(wordOfDayUi: WordOfDayUi)
}

class HomeNavigationImpl(
    private val navigator: AppNavigator
) : HomeNavigation {

    override fun navigateToWordOfDayDetail(wordOfDayUi: WordOfDayUi) {
        navigator.navigate(
            AppNavigationCommand.Navigate(
                AppRoutes.WordDetail.withArgs(wordOfDayUi.word, wordOfDayUi.pronunciation)
            )
        )
    }
}
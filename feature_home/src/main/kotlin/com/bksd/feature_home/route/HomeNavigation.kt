package com.bksd.feature_home.route

import com.bksd.route.AppNavigator

interface HomeNavigation {
   /* fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailUi)
    fun navigateToSearch()*/
}

class HomeNavigationImpl(
    private val navigator: AppNavigator
) : HomeNavigation {

    /*override fun navigateToWordOfDayDetail(wordOfDayUi: WordDetailUi) {
        navigator.navigate(
            NavigationAction.Navigate(
                AppRoutes.WordDetail.withArgs(wordOfDayUi.wordOfDay, wordOfDayUi.pronunciation.orEmpty())
            )
        )
    }

    override fun navigateToSearch() {
        navigator.navigate(
            NavigationAction.Navigate(AppRoutes.Search.route)
        )
    }*/
}
package com.bksd.feature_home.di

import com.bksd.feature_home.domain.usecase.GetRecentWordsUseCase
import com.bksd.feature_home.domain.usecase.GetWordDetailUseCase
import com.bksd.feature_home.domain.usecase.GetWordOfDayUseCase
import com.bksd.feature_home.domain.usecase.SetWordFavoriteUseCase
import com.bksd.feature_home.mapper.RecentWordsMapper
import com.bksd.feature_home.mapper.WordDetailMapper
import com.bksd.feature_home.mapper.WordOfDayMapper
import com.bksd.feature_home.route.HomeNavigation
import com.bksd.feature_home.route.HomeNavigationImpl
import com.bksd.feature_home.ui.HomeStore
import com.bksd.feature_home.ui.WordDetailStore
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel { HomeStore(wordOfDayUseCase = get(), get(), get(), get(), get()) }
    viewModel { WordDetailStore(get(),get(), get(), get()) }
    single<HomeNavigation> { HomeNavigationImpl(get()) }
    single { GetWordOfDayUseCase(get()) }
    single { GetRecentWordsUseCase(get()) }
    single { GetWordDetailUseCase(get()) }
    single { SetWordFavoriteUseCase(get()) }
    single { WordOfDayMapper() }
    single { RecentWordsMapper() }
    single { WordDetailMapper() }
}
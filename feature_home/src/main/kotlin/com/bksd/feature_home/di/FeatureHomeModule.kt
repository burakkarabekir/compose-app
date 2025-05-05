package com.bksd.feature_home.di

import com.bksd.feature_home.domain.usecase.GetRecentWordsUseCase
import com.bksd.feature_home.domain.usecase.GetWordOfDayUseCase
import com.bksd.feature_home.domain.usecase.GetWordUseCase
import com.bksd.feature_home.mapper.RecentWordsMapper
import com.bksd.feature_home.mapper.WordOfDayMapper
import com.bksd.feature_home.route.HomeNavigation
import com.bksd.feature_home.route.HomeNavigationImpl
import com.bksd.feature_home.ui.HomeVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel { HomeVM(/*get(),*/wordOfDayUseCase = get(), get(), get(), get(), get()) }
    single<HomeNavigation> { HomeNavigationImpl(get()) }
    single { GetWordUseCase(get()) }
    single { GetWordOfDayUseCase(get()) }
    single { GetRecentWordsUseCase(get()) }
    single { WordOfDayMapper() }
    single { RecentWordsMapper() }
}
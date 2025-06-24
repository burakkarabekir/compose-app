package com.bksd.feature_search.di

import com.bksd.feature_search.domain.usecase.GetWordUseCase
import com.bksd.feature_search.ui.SearchStore
import com.bksd.feature_search.ui.mapper.WordDetailToUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    viewModel { SearchStore(get(), mapper = get()) }
    single { GetWordUseCase(get()) }
    single { WordDetailToUiMapper() }
}
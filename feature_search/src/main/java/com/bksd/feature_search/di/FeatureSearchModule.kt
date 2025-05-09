package com.bksd.feature_search.di

import com.bksd.feature_search.domain.usecase.GetWordUseCase
import com.bksd.feature_search.ui.SearchVM
import com.bksd.feature_search.ui.mapper.WordDetailModelToUiMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    viewModel { SearchVM(get(), mapper = get()) }
    single { GetWordUseCase(get()) }
    single { WordDetailModelToUiMapper() }
}
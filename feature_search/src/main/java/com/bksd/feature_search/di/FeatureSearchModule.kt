package com.bksd.feature_search.di

import com.bksd.feature_search.ui.SearchVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    viewModel { SearchVM() }
}
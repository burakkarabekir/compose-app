package com.bksd.feature_home.di


import com.bksd.feature_home.route.HomeNavigation
import com.bksd.feature_home.route.HomeNavigationImpl
import com.bksd.feature_home.ui.HomeVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel { HomeVM() }
    single<HomeNavigation> { HomeNavigationImpl(get()) }
}
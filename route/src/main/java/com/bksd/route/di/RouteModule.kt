package com.bksd.route.di

import com.bksd.route.AppNavigator
import com.bksd.route.AppNavigatorImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val routeModule = module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate) }
    single<AppNavigator> { AppNavigatorImpl(get()) }
}
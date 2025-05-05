package com.bksd.composeapp.di

import android.app.Application
import com.bksd.composeapp.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

fun Application.initKoin(
    enableNetworkLogs: Boolean = BuildConfig.DEBUG,
    appModule: Module = module { },
    additionalModules: List<Module> = emptyList()
) {
    startKoin {
        androidLogger(level = takeIf { enableNetworkLogs }.let { Level.DEBUG })
        androidContext(this@initKoin)
        modules(appModule + additionalModules)
    }
}

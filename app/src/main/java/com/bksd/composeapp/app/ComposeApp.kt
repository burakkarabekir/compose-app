package com.bksd.composeapp.app

import android.app.Application
import com.bksd.composeapp.di.initKoin
import com.bksd.core_data.di.coreDataModule
import com.bksd.feature_home.di.featureHomeModule
import com.bksd.feature_search.di.featureSearchModule
import com.bksd.route.di.routeModule
import com.bksd.word_data.di.wordDataModule

class ComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            additionalModules = listOf(
                routeModule,
                coreDataModule,
                featureHomeModule,
                featureSearchModule,
                wordDataModule
            )
        )
    }
}
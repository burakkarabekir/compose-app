package com.bksd.composeapp.app

import android.app.Application
import com.bksd.composeapp.di.initKoin
import com.bksd.feature_home.di.featureHomeModule
import com.bksd.route.di.routeModule

class ComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(additionalModules = listOf(routeModule, featureHomeModule))
    }
}
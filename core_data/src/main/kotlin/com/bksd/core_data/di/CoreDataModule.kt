package com.bksd.core_data.di

import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.executor.InMemoryWordApiCache
import com.bksd.core_data.api.executor.WordApiCache
import com.bksd.core_data.api.executor.WordApiConfig
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.config.JsonProvider
import org.koin.dsl.module

val coreDataModule = module {
    single { WordApiConfig() }
    single { ApiRequestExecutor(config = get()) }
    single { JsonProvider.instance }
    single { ResponseMapper() }
    single { ExceptionMapper() }
    single<WordApiCache> { InMemoryWordApiCache.create() }
}
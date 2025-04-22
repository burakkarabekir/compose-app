package com.bksd.core_data.di

import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.api.WordApiServiceImpl
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.config.WordApiConfig
import com.bksd.core_data.dto.WordServiceResponse
import com.bksd.core_data.repository.WordRepositoryImpl
import com.bksd.core_domain.repository.WordRepository
import org.koin.dsl.module

val dataModule = module {
    // Repository
    single<WordRepository> { WordRepositoryImpl(wordApiService = get()) }
    single<WordApiService> { WordApiServiceImpl(get(), get(), get(), get()) }
    single<WordApiConfig> { WordApiConfig() }

    single { ApiRequestExecutor(config = get()) }
    single { ResponseMapper() }
    single { ExceptionMapper() }
    single { WordApiCache.create<WordServiceResponse>() }

    // API Service - using component-based implementation
    single<WordApiService> {
        WordApiServiceImpl(
            requestExecutor = get(),
            responseMapper = get(),
            exceptionMapper = get(),
            cache = get()
        )
    }
}
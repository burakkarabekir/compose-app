package com.bksd.core_data.di

import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.api.WordApiServiceImpl
import com.bksd.core_data.api.cache.InMemoryWordApiCache
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.RecentWordsResponseToDomainMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.api.mapper.WordResponseToDomainMapper
import com.bksd.core_data.config.JsonProvider
import com.bksd.core_data.config.WordApiConfig
import com.bksd.core_data.datasource.WordLocalDataSourceImpl
import com.bksd.core_data.datasource.WordRemoteDataSource
import com.bksd.core_data.datasource.WordRemoteDataSourceImpl
import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_data.dto.WordServiceResponse
import com.bksd.core_data.repository.WordRepositoryImpl
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordInformation
import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.repository.WordRepository
import org.koin.dsl.module

val dataModule = module {
    // Repository
    single<WordRepository> {
        WordRepositoryImpl(
            remoteDataSource = get(),
            localeDataSource = get(),
            mapper = get(),
            listMapper = get()
        )
    }
    single<WordRemoteDataSource> { WordRemoteDataSourceImpl(api = get(), exceptionMapper = get()) }
    single { WordLocalDataSourceImpl(get()) }
    single<WordApiService> { WordApiServiceImpl(get(), get(), get(), get()) }
    single<WordApiConfig> { WordApiConfig() }
    factory<BaseMapper<WordOfDayResponse, WordOfDayModel>> { WordResponseToDomainMapper() }
    factory<ListMapper<WordServiceResponse, WordInformation>> { RecentWordsResponseToDomainMapper() }
    single { JsonProvider.instance }
    single { ApiRequestExecutor(config = get()) }
    single { ResponseMapper() }
    single { ExceptionMapper() }
    single<WordApiCache> { InMemoryWordApiCache.create() }
    // API Service - using component-based implementation
    single<WordApiService> {
        WordApiServiceImpl(
            requestExecutor = get(),
            responseMapper = get(),
            exceptionMapper = get(),
            cache = get(),
        )
    }
}
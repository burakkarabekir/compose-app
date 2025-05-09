package com.bksd.core_data.di

import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.api.WordApiServiceImpl
import com.bksd.core_data.api.cache.InMemoryWordApiCache
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.RecentWordsResponseToDomainMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.api.mapper.WordDtoToDomainMapper
import com.bksd.core_data.api.mapper.WordResponseToDomainMapper
import com.bksd.core_data.config.JsonProvider
import com.bksd.core_data.config.WordApiConfig
import com.bksd.core_data.datasource.WordLocalDataSourceImpl
import com.bksd.core_data.datasource.WordRemoteDataSource
import com.bksd.core_data.datasource.WordRemoteDataSourceImpl
import com.bksd.core_data.dto.WordDto
import com.bksd.core_data.repository.WordRepositoryImpl
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordDetailModel
import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.repository.WordRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val WORD_OF_DAY_MAPPER = "wordOfDayMapper"
private const val RECENT_WORDS_MAPPER = "recentWordsMapper"
private const val WORD_INFO_MAPPER = "wordInfoMapper"

val dataModule = module {
    // --- Mappers (named for disambiguation) ---
    factory<BaseMapper<WordDto, WordOfDayModel>>(named(WORD_OF_DAY_MAPPER)) { WordResponseToDomainMapper() }
    factory<ListMapper<WordDto, WordDetailModel>>(named(RECENT_WORDS_MAPPER)) { RecentWordsResponseToDomainMapper() }
    factory<BaseMapper<WordDto, WordDetailModel>>(named(WORD_INFO_MAPPER)) { WordDtoToDomainMapper() }

    // --- API / Network ---
    single { WordApiConfig() }
    single { ApiRequestExecutor(config = get()) }
    single { JsonProvider.instance }
    single { ResponseMapper() }
    single { ExceptionMapper() }
    single<WordApiCache> { InMemoryWordApiCache.create() }
    single<WordApiService> {
        WordApiServiceImpl(
            requestExecutor = get(),
            responseMapper = get(),
            exceptionMapper = get(),
            cache = get()
        )
    }

    // --- Data Sources ---
    single<WordRemoteDataSource> { WordRemoteDataSourceImpl(api = get()) }
    single { WordLocalDataSourceImpl(get()) }

    // --- Repository ---
    single<WordRepository> {
        WordRepositoryImpl(
            remoteDataSource = get(),
            localeDataSource = get(),
            mapper = get(named(WORD_OF_DAY_MAPPER)),
            listMapper = get(named(RECENT_WORDS_MAPPER)),
            wordMapper = get(named(WORD_INFO_MAPPER))
        )
    }
}
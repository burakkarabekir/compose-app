package com.bksd.core_data.di

import androidx.room.Room
import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.api.WordApiServiceImpl
import com.bksd.core_data.api.cache.InMemoryWordApiCache
import com.bksd.core_data.api.cache.WordApiCache
import com.bksd.core_data.api.executor.ApiRequestExecutor
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.api.mapper.ResponseMapper
import com.bksd.core_data.api.mapper.WordDtoToDomainMapper
import com.bksd.core_data.api.mapper.WordDtoToEntityMapper
import com.bksd.core_data.api.mapper.WordEntityToDomainListMapper
import com.bksd.core_data.api.mapper.WordEntityToDomainMapper
import com.bksd.core_data.api.mapper.WordResponseToDomainMapper
import com.bksd.core_data.config.JsonProvider
import com.bksd.core_data.config.WordApiConfig
import com.bksd.core_data.local.datasource.WordLocalDataSource
import com.bksd.core_data.local.datasource.WordLocalDataSourceImpl
import com.bksd.core_data.local.db.AppDatabase
import com.bksd.core_data.local.entity.WordEntity
import com.bksd.core_data.remote.datasource.WordRemoteDataSource
import com.bksd.core_data.remote.datasource.WordRemoteDataSourceImpl
import com.bksd.core_data.remote.dto.WordDto
import com.bksd.core_data.repository.WordRepositoryImpl
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordDetail
import com.bksd.core_domain.model.WordOfDay
import com.bksd.core_domain.repository.WordRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val WORD_OF_DAY_MAPPER = "wordOfDayMapper"
private const val RECENT_WORDS_MAPPER = "recentWordsMapper"
private const val WORD_DTO_TO_ENTITY_MAPPER = "wordDtoToEntityMapper"
private const val WORD_ENTITY_TO_MODEL_MAPPER = "wordEntityToModelMapper"
private const val WORD_INFO_MAPPER = "wordInfoMapper"

val dataModule = module {
    // --- Mappers (named for disambiguation) ---
    factory<BaseMapper<WordDto, WordOfDay>>(named(WORD_OF_DAY_MAPPER)) { WordResponseToDomainMapper() }
    factory<ListMapper<WordEntity, WordDetail>>(named(RECENT_WORDS_MAPPER)) { WordEntityToDomainListMapper() }
    factory<BaseMapper<WordDto, WordDetail>>(named(WORD_INFO_MAPPER)) { WordDtoToDomainMapper() }

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

    // --- Repository ---
    single<WordRepository> {
        WordRepositoryImpl(
            remoteDataSource = get(),
            localeDataSource = get(),
            mapper = get(named(WORD_OF_DAY_MAPPER)),
            recentWordsMapper = get(named(RECENT_WORDS_MAPPER)),
            wordEntityToModelMapper = get(named(WORD_ENTITY_TO_MODEL_MAPPER)),
            wordDtoToEntityMapper = get(named(WORD_DTO_TO_ENTITY_MAPPER)),
            wordMapper = get(named(WORD_INFO_MAPPER))
        )
    }

    // --- Local ---
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "word-db"
        ).build()
    }
    single<WordLocalDataSource> { WordLocalDataSourceImpl(wordDao = get()) }
    single { get<AppDatabase>().wordDao() }
    factory<BaseMapper<WordDto, WordEntity>>(named(WORD_DTO_TO_ENTITY_MAPPER)) { WordDtoToEntityMapper() }
    factory<BaseMapper<WordEntity, WordDetail>>(named(WORD_ENTITY_TO_MODEL_MAPPER)) { WordEntityToDomainMapper() }

    // --- Remote ---
    single<WordRemoteDataSource> { WordRemoteDataSourceImpl(api = get()) }
}
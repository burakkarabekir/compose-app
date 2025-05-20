package com.bksd.word_data.remote.datasource

import com.bksd.core_data.network.DataState
import com.bksd.word_data.remote.dto.WordDto
import com.bksd.word_data.service.WordApiService

class WordRemoteDataSourceImpl(
    private val api: WordApiService,
) : WordRemoteDataSource {
    override suspend fun fetchWord(word: String): DataState<WordDto> =
        DataState.Success(api.fetchWord(word))

    override suspend fun fetchWordOfDay(): DataState<WordDto> =
        DataState.Success(api.fetchWordOfDay())
}
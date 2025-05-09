package com.bksd.core_data.datasource

import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.dto.WordDto
import com.bksd.core_data.network.DataState

class WordRemoteDataSourceImpl(
    private val api: WordApiService,
) : WordRemoteDataSource {
    override suspend fun fetchWord(word: String): DataState<WordDto> =
        DataState.Success(api.getWordInformation(word))

    override suspend fun fetchWordOfDay(): DataState<WordDto> =
        DataState.Success(api.getWordOfDay())
}
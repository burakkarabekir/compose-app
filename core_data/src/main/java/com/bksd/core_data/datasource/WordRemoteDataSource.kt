package com.bksd.core_data.datasource

import com.bksd.core_data.dto.WordDto
import com.bksd.core_data.network.DataState

interface WordRemoteDataSource {
    suspend fun fetchWord(word: String): DataState<WordDto>
    suspend fun fetchWordOfDay(): DataState<WordDto>
}
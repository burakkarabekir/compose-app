package com.bksd.core_data.remote.datasource

import com.bksd.core_data.network.DataState
import com.bksd.core_data.remote.dto.WordDto

interface WordRemoteDataSource {
    suspend fun fetchWord(word: String): DataState<WordDto>
    suspend fun fetchWordOfDay(): DataState<WordDto>
}
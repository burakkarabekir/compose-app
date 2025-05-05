package com.bksd.core_data.datasource

import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_data.network.DataState

interface WordRemoteDataSource {
    // suspend fun fetchWord(word: String): DataState<WordServiceResponse>
    suspend fun fetchWordOfDay(): DataState<WordOfDayResponse>
}
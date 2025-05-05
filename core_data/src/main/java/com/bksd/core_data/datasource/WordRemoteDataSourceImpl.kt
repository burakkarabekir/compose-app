package com.bksd.core_data.datasource

import com.bksd.core_data.api.WordApiService
import com.bksd.core_data.api.mapper.ExceptionMapper
import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_data.network.DataState

class WordRemoteDataSourceImpl(
    private val api: WordApiService,
    private val exceptionMapper: ExceptionMapper
) : WordRemoteDataSource {
    override suspend fun fetchWordOfDay(): DataState<WordOfDayResponse> = try {
        val result = api.getWordOfDay()
        DataState.Success(result)
    } catch (e: Exception) {
        DataState.Error(exceptionMapper.mapException(e))
    }
}
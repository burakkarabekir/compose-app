package com.bksd.core_data.datasource

import com.bksd.core_data.dto.WordServiceResponse

interface WordLocalDataSource {
    suspend fun getCachedWords(): List<WordServiceResponse>?
}
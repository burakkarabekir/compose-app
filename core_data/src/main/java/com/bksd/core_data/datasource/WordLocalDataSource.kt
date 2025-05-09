package com.bksd.core_data.datasource

import com.bksd.core_data.dto.WordDto

interface WordLocalDataSource {
    suspend fun getCachedWords(): List<WordDto>?
}
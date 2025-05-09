package com.bksd.core_data.local.datasource

import com.bksd.core_data.remote.dto.WordDto

interface WordLocalDataSource {
    suspend fun getCachedWords(): List<WordDto>?
}
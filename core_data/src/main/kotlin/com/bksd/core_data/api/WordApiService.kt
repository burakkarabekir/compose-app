package com.bksd.core_data.api

import com.bksd.core_data.remote.dto.WordDto
import io.ktor.client.statement.HttpResponse

interface WordApiService {
    suspend fun fetchWord(word: String): WordDto
    suspend fun fetchWordOfDay(): WordDto
    suspend fun getWordDefinition(word: String): WordDto
    suspend fun getWordSynonyms(word: String): WordDto
    suspend fun getWordPronunciation(word: String): WordDto
    suspend fun executeRawRequest(
        endpoint: String,
        word: String,
        params: Map<String, String> = emptyMap()
    ): HttpResponse

    fun clearCache()
}
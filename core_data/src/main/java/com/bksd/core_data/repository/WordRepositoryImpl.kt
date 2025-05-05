package com.bksd.core_data.repository

import com.bksd.core_data.datasource.WordLocalDataSourceImpl
import com.bksd.core_data.datasource.WordRemoteDataSource
import com.bksd.core_data.dto.WordOfDayResponse
import com.bksd.core_data.dto.WordServiceResponse
import com.bksd.core_domain.exception.WordApiException
import com.bksd.core_domain.exception.WordNotFoundException
import com.bksd.core_domain.mapper.BaseMapper
import com.bksd.core_domain.mapper.ListMapper
import com.bksd.core_domain.model.WordInformation
import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of WordRepository that uses WordApiService
 * to fetch word data and maps it to domain models.
 *
 * @property wordApiService The service used to access the Word API
 */
class WordRepositoryImpl(
    private val remoteDataSource: WordRemoteDataSource,
    private val localeDataSource: WordLocalDataSourceImpl,
    private val mapper: BaseMapper<WordOfDayResponse, WordOfDayModel>,
    private val listMapper: ListMapper<WordServiceResponse, WordInformation>,
//    private val exceptionMapper: ExceptionMapper
) : WordRepository {

    /* override suspend fun getWordInformation(
         word: String
     ): Flow<DomainResult<WordInformation>> = wordApiService.getWordInformation(word)
         .map { response -> mapToWordInformation(response) }
         .catch { throwable -> throw mapToRepositoryException(throwable, word) }
 */

    override suspend fun getWordOfDay(): Flow<DomainResult<WordOfDayModel>> = flow {
        emit(DomainResult.Loading)
        val response = remoteDataSource.fetchWordOfDay()

        response.getOrNull()?.let {
            val result: WordOfDayModel = try {
                mapper.map(it)
            } catch (e: Exception) {
                emit(DomainResult.Error(e, e.message.orEmpty()))
                return@flow
            }
            emit(DomainResult.Success(result))
        } ?: emit(DomainResult.Empty)
    }

    override suspend fun getRecentWords(): Flow<DomainResult<List<WordInformation>>> = flow {
        emit(DomainResult.Loading)
        val response = localeDataSource.getCachedWords()
            ?: listOf(WordServiceResponse("deneme"), WordServiceResponse("deneme2"))

        response.let {
            val result: List<WordInformation> = try {
                listMapper.map(it)
            } catch (e: Exception) {
                emit(DomainResult.Error(e, e.message.orEmpty()))
                return@flow
            }
            emit(DomainResult.Success(result))
        }
    }

    /* override suspend fun getWordOfDay(): Flow<DomainResult<WordOfDayModel>> = wordApiService.getWordOfDay()
         .map { response ->  DomainResult.Success(
             WordOfDayModel(
                 word = response.word.orEmpty(),
                 pronunciation = response.pronunciation?.all.orEmpty(),
                 definition = response.results?.firstOrNull()?.definition.orEmpty(),
             )
             )
         }
         .catch { throwable -> throw mapToRepositoryException(throwable = throwable, word = null) }
 */
    /*override suspend fun getWordDefinition(word: String): Flow<WordServiceResponse> {
        return wordApiService.getWordDefinition(word)
            .map { response ->
                WordServiceResponse(
                    word = response.word,
                    definitions = response.results.map { result ->
                        mapToDefinition(result)
                    }
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun getWordSynonyms(word: String): Flow<WordSynonyms> {
        return wordApiService.getWordSynonyms(word)
            .map { response ->
                WordSynonyms(
                    word = response.word,
                    synonyms = response.results.flatMap { it.synonyms ?: emptyList() }.distinct()
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun getWordPronunciation(word: String): Flow<WordPronunciation> {
        return wordApiService.getWordPronunciation(word)
            .map { response ->
                WordPronunciation(
                    word = response.word,
                    pronunciation = response.pronunciation?.all
                )
            }
            .catch { throwable ->
                throw mapToRepositoryException(throwable, word)
            }
    }

    override suspend fun wordExists(word: String): Flow<Boolean> {
        return try {
            wordApiService.getWordInformation(word)
                .map { true }
                .catch { throwable ->
                    if (throwable is WordNotFoundException) {
                        emit(false)
                    } else {
                        throw mapToRepositoryException(throwable, word)
                    }
                }
        } catch (e: Exception) {
            throw mapToRepositoryException(e, word)
        }
    }

    */
    /**
     * Maps a Result DTO to a Definition domain model.
     *//*
    private fun mapToDefinition(result: com.example.core.data.dto.Result): Definition {
        return Definition(
            text = result.definition,
            partOfSpeech = result.partOfSpeech,
            synonyms = result.synonyms ?: emptyList(),
            related = RelatedWords(
                typeOf = result.typeOf ?: emptyList(),
                memberOf = result.memberOf ?: emptyList(),
                hasTypes = result.hasTypes ?: emptyList(),
                partOf = result.partOf ?: emptyList()
            )
        )
    }*/

    /**
     * Maps a WordServiceResponse to a WordInformation domain model.
     */
    private fun mapToWordInformation(response: WordServiceResponse): DomainResult<WordInformation> =
        DomainResult.Success(
            WordInformation(
                text = response.word,
                /* definitions = response.results.map { mapToDefinition(it) },
                 synonyms = response.results.flatMap { it.synonyms ?: emptyList() }.distinct(),
                 pronunciation = response.pronunciation?.all,
                 syllables = response.syllables?.let {
                     SyllableInfo(it.count, it.list)
                 },
                 frequency = response.frequency*/
            )
        )

    /**
     * Maps API exceptions to repository exceptions.
     */
    private fun mapToRepositoryException(throwable: Throwable, word: String?): Exception =
        when (throwable) {
            is WordNotFoundException -> WordNotInRepositoryException(word.orEmpty())
            is WordApiException -> WordRepositoryException(
                "API error while processing word '$word'",
                throwable
            )

            else -> WordRepositoryException(
                "Unexpected error while processing word '$word'",
                throwable
            )
        }
}

/**
 * Exception thrown when a word is not found in the repository.
 */
class WordNotInRepositoryException(word: String) :
    Exception("Word not found in repository: '$word'")

/**
 * General exception for repository-related errors.
 */
class WordRepositoryException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
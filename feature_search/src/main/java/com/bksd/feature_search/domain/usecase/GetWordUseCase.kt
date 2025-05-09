package com.bksd.feature_search.domain.usecase

import com.bksd.core_domain.model.WordDetail
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetWordUseCase(
    private val repository: WordRepository
) : FlowUseCase<GetWordUseCase.Params, DomainResult<WordDetail>>() {

    data class Params(val word: String)

    override suspend fun invoke(params: Params): Flow<DomainResult<WordDetail>> =
        repository.getWordInformation(params.word)
}
package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.model.WordInformation
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordUseCase(
    private val repository: WordRepository
) : FlowUseCase<GetWordUseCase.Params, DomainResult<WordInformation>>() {

    data class Params(val word: String)

    override suspend fun invoke(params: Params): Flow<DomainResult<WordInformation>> =
        // repository.getWordInformation(params.word)
        flow { }
}
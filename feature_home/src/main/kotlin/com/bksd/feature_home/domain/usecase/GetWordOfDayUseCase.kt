package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import com.bksd.word_domain.model.WordOfDay
import com.bksd.word_domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordOfDayUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<WordOfDay>>() {
    override suspend operator fun invoke(params: Unit): Flow<DomainResult<WordOfDay>> =
        repository.getWordOfDay()
}
package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.model.WordOfDayModel
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetWordOfDayUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<WordOfDayModel>>() {
    override suspend operator fun invoke(params: Unit): Flow<DomainResult<WordOfDayModel>> =
        repository.getWordOfDay()
}
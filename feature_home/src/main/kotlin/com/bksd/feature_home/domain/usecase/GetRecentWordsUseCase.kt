package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.model.WordDetailModel
import com.bksd.core_domain.repository.WordRepository
import com.bksd.core_domain.result.DomainResult
import com.bksd.core_domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow

class GetRecentWordsUseCase(
    private val repository: WordRepository
) : FlowUseCase<Unit, DomainResult<List<WordDetailModel>>>() {

    override suspend fun invoke(params: Unit): Flow<DomainResult<List<WordDetailModel>>> =
        repository.getRecentWords()
}
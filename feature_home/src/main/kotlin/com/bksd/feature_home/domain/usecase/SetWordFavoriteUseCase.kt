package com.bksd.feature_home.domain.usecase

import com.bksd.core_domain.usecase.UseCaseNoResult
import com.bksd.word_domain.repository.WordRepository

class SetWordFavoriteUseCase(
    private val repository: WordRepository
) : UseCaseNoResult<Pair<String, Boolean>, Unit>() {
    override suspend operator fun invoke(params: Pair<String, Boolean>): Unit =
        repository.setWordFavorite(params.first, params.second)
}
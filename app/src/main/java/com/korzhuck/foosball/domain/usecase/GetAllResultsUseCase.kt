package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetAllResultsUseCase {
    operator fun invoke(): Single<List<MatchResult>>
}

class GetAllResultsUseCaseImpl @Inject constructor(
    private val resultsRepository: ResultsRepository,
) : GetAllResultsUseCase {
    override fun invoke(): Single<List<MatchResult>> = resultsRepository.getAll()
}

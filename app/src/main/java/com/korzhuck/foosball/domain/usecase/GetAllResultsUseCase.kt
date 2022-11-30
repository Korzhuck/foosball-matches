package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface GetAllResultsUseCase {
    operator fun invoke(): Observable<List<MatchResult>>
}

class GetAllResultsUseCaseImpl @Inject constructor(
    private val resultsRepository: ResultsRepository,
) : GetAllResultsUseCase {
    override fun invoke(): Observable<List<MatchResult>> = resultsRepository.allResults
}

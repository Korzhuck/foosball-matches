package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface RemoveResultUseCase {
    operator fun invoke(matchResult: MatchResult): Completable
}

class RemoveResultUseCaseImpl @Inject constructor(
    private val resultsRepository: ResultsRepository,
) : RemoveResultUseCase {
    override fun invoke(matchResult: MatchResult): Completable =
        resultsRepository.removeResult(matchResult)
}

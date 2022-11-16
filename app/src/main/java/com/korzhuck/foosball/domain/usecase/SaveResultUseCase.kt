package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface SaveResultUseCase {
    operator fun invoke(matchResult: MatchResult): Completable
}

class SaveResultUseCaseImpl @Inject constructor(
    private val resultsRepository: ResultsRepository,
) : SaveResultUseCase {
    override fun invoke(matchResult: MatchResult): Completable =
        resultsRepository.saveResult(matchResult)
}

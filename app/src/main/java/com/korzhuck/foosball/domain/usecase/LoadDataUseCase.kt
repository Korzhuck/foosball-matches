package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.ResultsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface LoadDataUseCase {
    operator fun invoke(): Completable
}

class LoadDataUseCaseImpl @Inject constructor(
    private val resultsRepository: ResultsRepository,
) : LoadDataUseCase {
    override fun invoke(): Completable = resultsRepository.loadResults()
}

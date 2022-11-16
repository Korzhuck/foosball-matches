package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetAllRankingsUseCase {
    operator fun invoke(): Single<List<PlayerRanking>>
}

class GetAllRankingsUseCaseImpl @Inject constructor(
    private val rankingsRepository: RankingsRepository,
) : GetAllRankingsUseCase {
    override fun invoke(): Single<List<PlayerRanking>> = rankingsRepository.getAll()
}

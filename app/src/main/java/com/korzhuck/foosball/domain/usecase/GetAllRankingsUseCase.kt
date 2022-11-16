package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetAllRankingsUseCase {
    operator fun invoke(sortOrder: SortOrder): Single<List<PlayerRanking>>
}

class GetAllRankingsUseCaseImpl @Inject constructor(
    private val rankingsRepository: RankingsRepository,
) : GetAllRankingsUseCase {
    override fun invoke(sortOrder: SortOrder): Single<List<PlayerRanking>> =
        rankingsRepository.getAll()
        .toSortedList(sortOrder.comparator)
}

enum class SortOrder(val comparator: Comparator<PlayerRanking>) {
    Matches(comparator = { o1, o2 -> o2.matchesCount.compareTo(o1.matchesCount)}),
    Wins(comparator = { o1, o2 -> o2.winsCount.compareTo(o1.winsCount)}),
}

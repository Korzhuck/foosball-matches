package com.korzhuck.foosball.data.repositories

import com.korzhuck.foosball.data.source.InMemoryDataSource
import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class RankingsRepositoryImpl(
    private val dataSource: InMemoryDataSource,
) : RankingsRepository {
    override fun getAll(): Single<List<PlayerRanking>> =
        dataSource.matchesResults
            .flatMapObservable { Observable.fromIterable(it) }
            .flatMap { Observable.fromIterable(it.toRankings()) }
            .groupBy(PlayerRanking::player)
            .flatMapMaybe { group ->
                group.reduce { aggregate, result ->
                    PlayerRanking(
                        player = group.key!!,
                        matchesCount = aggregate.matchesCount + result.matchesCount,
                        winsCount = aggregate.winsCount + result.winsCount
                    )
                }
            }
            .toSortedList { o1, o2 ->
                o1.winsCount.compareTo(o2.winsCount)
            }
}

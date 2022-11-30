package com.korzhuck.foosball.data.repositories

import com.korzhuck.foosball.data.source.InMemoryDataSource
import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RankingsRepositoryImpl @Inject constructor(
    private val dataSource: InMemoryDataSource,
) : RankingsRepository {
    override fun getAll(): Observable<List<PlayerRanking>> =
        dataSource.matchesResults
            .flatMap { results ->
                Observable.fromIterable(results)
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
                }.toList().toObservable()
            }
}

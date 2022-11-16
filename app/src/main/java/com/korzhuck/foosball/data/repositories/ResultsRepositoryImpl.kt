package com.korzhuck.foosball.data.repositories

import com.korzhuck.foosball.data.source.InMemoryDataSource
import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ResultsRepositoryImpl @Inject constructor(
    private val dataSource: InMemoryDataSource,
) : ResultsRepository {
    override fun getAll(): Single<List<MatchResult>> = dataSource.matchesResults.map {
        it.toList()
    }

    override fun removeResult(matchResult: MatchResult): Completable =
        dataSource.removeResult(matchResult)

    override fun saveResult(matchResult: MatchResult): Completable =
        dataSource.saveResult(matchResult)
}

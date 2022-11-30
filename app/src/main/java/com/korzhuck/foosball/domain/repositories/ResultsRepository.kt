package com.korzhuck.foosball.domain.repositories

import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface ResultsRepository {
    val allResults: Observable<List<MatchResult>>

    fun removeResult(matchResult: MatchResult): Completable

    fun saveResult(matchResult: MatchResult): Completable

    fun loadResults(): Completable
}

package com.korzhuck.foosball.data.source

import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.models.Player
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class InMemoryDataSource @Inject constructor(
    matchResults: List<MatchResult>,
) {
    private val _matchesResults = matchResults.toMutableList()

    val matchesResults: Subject<List<MatchResult>> = BehaviorSubject.createDefault(_matchesResults)

    fun removeResult(matchResult: MatchResult): Completable =
        Completable.create {
            this._matchesResults.remove(matchResult)
            it.onComplete()
        }.doOnComplete { matchesResults.onNext(_matchesResults) }

    fun saveResult(matchResult: MatchResult): Completable =
        Completable.create {
            val index = this._matchesResults.indexOf(matchResult)
            if (index == -1) {
                this._matchesResults.add(matchResult.copy(id = _matchesResults.size))
            } else {
                this._matchesResults[index] = matchResult
            }
            it.onComplete()
        }.doOnComplete { matchesResults.onNext(_matchesResults) }

    fun loadResults(): Completable =
        // timer is used to show some interaction
        Completable.timer(1, TimeUnit.SECONDS)
            .doOnComplete { _matchesResults.addAll(initialResults) }
            .doOnComplete { matchesResults.onNext(_matchesResults) }

    companion object {
        val initialResults = listOf(
            MatchResult(1, Player(name = "Amos"), 4, Player("Diego"), 5),
            MatchResult(2, Player(name = "Amos"), 1, Player("Diego"), 5),
            MatchResult(3, Player(name = "Amos"), 2, Player("Diego"), 5),
            MatchResult(4, Player(name = "Amos"), 0, Player("Diego"), 5),
            MatchResult(5, Player(name = "Amos"), 6, Player("Diego"), 5),
            MatchResult(6, Player(name = "Amos"), 5, Player("Diego"), 2),
            MatchResult(7, Player(name = "Amos"), 4, Player("Diego"), 0),
            MatchResult(8, Player(name = "Joel"), 4, Player("Diego"), 5),
            MatchResult(9, Player(name = "Tim"), 4, Player("Amos"), 5),
            MatchResult(10,Player(name = "Tim"), 5, Player("Amos"), 2),
            MatchResult(11, Player(name = "Amos"), 3, Player("Tim"), 5),
            MatchResult(12, Player(name = "Amos"), 5, Player("Tim"), 3),
            MatchResult(13, Player(name = "Amos"), 5, Player("Joel"), 4),
            MatchResult(14, Player(name = "Joel"), 5, Player("Tim"), 2),
        )
    }
}

package com.korzhuck.foosball.data.source

import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.models.Player
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class InMemoryDataSource @Inject constructor(
    matchResults: List<MatchResult>,
) {
    private val _matches = matchResults.toMutableList()

    val matches: Single<List<MatchResult>>
        get() = Single.just(_matches)


    companion object {
        val initialResults = listOf(
            MatchResult(Player(name = "Amos"), 4, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 1, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 2, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 0, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 6, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 5, Player("Diego"), 2),
            MatchResult(Player(name = "Amos"), 4, Player("Diego"), 0),
            MatchResult(Player(name = "Joel"), 4, Player("Diego"), 5),
            MatchResult(Player(name = "Tim"), 4, Player("Diego"), 5),
            MatchResult(Player(name = "Tim"), 5, Player("Diego"), 2),
            MatchResult(Player(name = "Amos"), 3, Player("Diego"), 5),
            MatchResult(Player(name = "Amos"), 5, Player("Diego"), 3),
            MatchResult(Player(name = "Amos"), 5, Player("Diego"), 4),
            MatchResult(Player(name = "Joel"), 5, Player("Diego"), 2),
        )
    }
}

package com.korzhuck.foosball.data.repositories

import com.korzhuck.foosball.Mocks
import com.korzhuck.foosball.data.source.InMemoryDataSource
import com.korzhuck.foosball.models.Player
import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class RankingsRepositoryImplTest {
    private val matches = listOf(Mocks.matchResult, Mocks.matchResult)
    private val dataSource = InMemoryDataSource(matchResults = matches)

    private val repository = RankingsRepositoryImpl(
        dataSource = dataSource,
    )

    @Test
    fun onGetAllShouldCombineResultByPlayers() {
        val testObserver = TestObserver.create<PlayerRanking>()

        repository.getAll().subscribe(testObserver)

        testObserver.assertResult(
            PlayerRanking(Player("Player 2"), 2, 2),
            PlayerRanking(Player("Player 1"), 2, 0),

        )
    }
}

package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.Player
import com.korzhuck.foosball.models.PlayerRanking
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class GetAllRankingsUseCaseImplTest {
    private val rankings = listOf(
        PlayerRanking(Player("Player 1"), 2, 0),
        PlayerRanking(Player("Player 2"), 1, 1),
    )
    private val rankingsRepository: RankingsRepository = mock {
        on { getAll() } doReturn Observable.fromIterable(rankings)
    }

    private val useCase = GetAllRankingsUseCaseImpl(
        rankingsRepository = rankingsRepository,
    )

    @Test
    fun onInvokeWithMatchesShouldReturnDataFromRepository() {
        val testObserver = TestObserver.create<List<PlayerRanking>>()

        useCase(SortOrder.Matches).subscribe(testObserver)

        testObserver.assertResult(rankings)
    }

    @Test
    fun onInvokeWithWinsShouldReturnDataFromRepository() {
        val testObserver = TestObserver.create<List<PlayerRanking>>()

        useCase(SortOrder.Wins).subscribe(testObserver)

        testObserver.assertResult(rankings.reversed())
    }
}

package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.Mocks
import com.korzhuck.foosball.domain.repositories.RankingsRepository
import com.korzhuck.foosball.models.PlayerRanking
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class GetAllRankingsUseCaseImplTest {
    private val rankings = Mocks.matchResult.toRankings()
    private val rankingsRepository: RankingsRepository = mock {
        on { getAll() } doReturn Single.just(rankings)
    }

    private val useCase = GetAllRankingsUseCaseImpl(
        rankingsRepository = rankingsRepository,
    )

    @Test
    fun onInvokeShouldReturnDataFromRepository() {
        val testObserver = TestObserver.create<List<PlayerRanking>>()

        useCase().subscribe(testObserver)

        testObserver.assertResult(rankings)
    }
}

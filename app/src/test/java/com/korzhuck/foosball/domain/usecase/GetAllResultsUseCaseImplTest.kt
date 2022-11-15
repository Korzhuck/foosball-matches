package com.korzhuck.foosball.domain.usecase

import com.korzhuck.foosball.Mocks
import com.korzhuck.foosball.domain.repositories.ResultsRepository
import com.korzhuck.foosball.models.MatchResult
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class GetAllResultsUseCaseImplTest {
    private val matches = listOf(Mocks.matchResult, Mocks.matchResult)
    private val resultsRepository: ResultsRepository = mock {
        on { getAll() } doReturn Single.just(matches)
    }

    private val useCase = GetAllResultsUseCaseImpl(
        resultsRepository = resultsRepository,
    )

    @Test
    fun onInvokeShouldReturnDataFromRepository() {
        val testObserver = TestObserver.create<List<MatchResult>>()

        useCase().subscribe(testObserver)

        testObserver.assertResult(matches)
    }
}

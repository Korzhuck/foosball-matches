package com.korzhuck.foosball.data.repositories

import com.korzhuck.foosball.Mocks
import com.korzhuck.foosball.data.source.InMemoryDataSource
import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test

class ResultsRepositoryImplTest {
    private val matches = listOf(Mocks.matchResult, Mocks.matchResult)
    private val dataSource = InMemoryDataSource(matchResults = matches)
    private val repository = ResultsRepositoryImpl(
        dataSource = dataSource,
    )

    @Test
    fun onGetAllShouldReturnAll() {
        val testObserver = TestObserver.create<List<MatchResult>>()

        repository.getAll().subscribe(testObserver)

        testObserver.assertResult(matches)
    }
}

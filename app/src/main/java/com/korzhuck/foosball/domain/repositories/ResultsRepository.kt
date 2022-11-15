package com.korzhuck.foosball.domain.repositories

import com.korzhuck.foosball.models.MatchResult
import io.reactivex.rxjava3.core.Single

interface ResultsRepository {
    fun getAll(): Single<List<MatchResult>>
}
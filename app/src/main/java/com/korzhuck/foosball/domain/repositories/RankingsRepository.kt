package com.korzhuck.foosball.domain.repositories

import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Observable

interface RankingsRepository {
    fun getAll(): Observable<List<PlayerRanking>>
}

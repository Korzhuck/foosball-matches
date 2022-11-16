package com.korzhuck.foosball.domain.repositories

import com.korzhuck.foosball.models.PlayerRanking
import io.reactivex.rxjava3.core.Single

interface RankingsRepository {
    fun getAll(): Single<List<PlayerRanking>>
}

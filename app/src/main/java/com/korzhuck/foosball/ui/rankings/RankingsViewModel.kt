package com.korzhuck.foosball.ui.rankings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.GetAllRankingsUseCase
import com.korzhuck.foosball.models.PlayerRanking
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingsViewModel @Inject constructor(
    getAllRankings: GetAllRankingsUseCase,
    schedulers: AppSchedulers,
) : BaseRxViewModel() {

    private val _rankings = MutableLiveData<List<PlayerRanking>>()
    val rankings: LiveData<List<PlayerRanking>> = _rankings

    init {
        getAllRankings()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe { results ->
                _rankings.value = results
            }
            .autoDispose()
    }
}

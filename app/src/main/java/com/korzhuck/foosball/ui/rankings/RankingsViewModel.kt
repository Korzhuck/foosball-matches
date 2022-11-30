package com.korzhuck.foosball.ui.rankings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.GetAllRankingsUseCase
import com.korzhuck.foosball.domain.usecase.SortOrder
import com.korzhuck.foosball.models.PlayerRanking
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingsViewModel @Inject constructor(
    private val getAllRankings: GetAllRankingsUseCase,
    private val schedulers: AppSchedulers,
) : BaseRxViewModel() {

    private val _rankings = MutableLiveData<List<PlayerRanking>>()
    val rankings: LiveData<List<PlayerRanking>> = _rankings

    private val _sortOrder = MutableLiveData<SortOrder>()
    val sortOrder: LiveData<SortOrder> = _sortOrder

    fun initialize() {
        if (sortOrder.value == null) {
            _sortOrder.value = DEFAULT_SORT_ORDER
        }
    }

    fun loadRankings(sortOrder: SortOrder) {
        getAllRankings(sortOrder)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe { results ->
                _rankings.value = results
            }
            .autoDispose()
    }

    fun onSortChanged(sortOrder: SortOrder) {
        _sortOrder.value = sortOrder
    }

    companion object {
        private val DEFAULT_SORT_ORDER = SortOrder.Matches
    }
}

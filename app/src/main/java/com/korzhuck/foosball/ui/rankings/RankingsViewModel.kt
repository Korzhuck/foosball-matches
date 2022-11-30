package com.korzhuck.foosball.ui.rankings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.GetAllRankingsUseCase
import com.korzhuck.foosball.domain.usecase.SortOrder
import com.korzhuck.foosball.models.PlayerRanking
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject

@HiltViewModel
class RankingsViewModel @Inject constructor(
    private val getAllRankings: GetAllRankingsUseCase,
    private val schedulers: AppSchedulers,
) : BaseRxViewModel() {

    private val _rankings = MutableLiveData<List<PlayerRanking>>()
    val rankings: LiveData<List<PlayerRanking>> = _rankings

    val sortOrder: Subject<SortOrder> = BehaviorSubject.createDefault(DEFAULT_SORT_ORDER)

    fun initialize() {
        disposables += Observable.combineLatest(
            getAllRankings(),
            sortOrder,
        ) { rankings, order ->
            rankings.sortedWith(order.comparator)
        }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe { results ->
                _rankings.value = results
            }
    }

    companion object {
        private val DEFAULT_SORT_ORDER = SortOrder.Matches
    }
}

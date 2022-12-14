package com.korzhuck.foosball.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.GetAllResultsUseCase
import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val getAllResults: GetAllResultsUseCase,
    private val schedulers: AppSchedulers,
) : BaseRxViewModel() {
    private val _results = MutableLiveData<List<MatchResult>>()
    val results: LiveData<List<MatchResult>> = _results

    fun initialize() {
        disposables += getAllResults()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe { results ->
                _results.value = results
            }
    }
}

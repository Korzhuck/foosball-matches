package com.korzhuck.foosball.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.GetAllResultsUseCase
import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    getAllResults: GetAllResultsUseCase,
    schedulers: AppSchedulers,
) : BaseRxViewModel() {
    private val _results = MutableLiveData<List<MatchResult>>()
    val results: LiveData<List<MatchResult>> = _results

    init {
        getAllResults()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe { results ->
                _results.value = results
            }
            .autoDispose()
    }
}

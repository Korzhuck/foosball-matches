package com.korzhuck.foosball

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.domain.usecase.LoadDataUseCase
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import com.korzhuck.foosball.utils.AppSchedulers
import com.korzhuck.foosball.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    loadData: LoadDataUseCase,
    schedulers: AppSchedulers,
) : BaseRxViewModel() {

    private val _state = MutableLiveData<State<Unit>>().apply {
        value = State.Loading()
    }
    val state: LiveData<State<Unit>> = _state

    init {
        disposables += loadData()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe {
                _state.value = State.Success(Unit)
            }
    }
}

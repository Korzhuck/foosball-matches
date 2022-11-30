package com.korzhuck.foosball.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseRxViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()

    override fun onCleared() = disposables.clear()
}

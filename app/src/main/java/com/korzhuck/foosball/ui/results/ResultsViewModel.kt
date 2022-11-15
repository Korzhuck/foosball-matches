package com.korzhuck.foosball.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor() : BaseRxViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Results Fragment"
    }
    val text: LiveData<String> = _text
}

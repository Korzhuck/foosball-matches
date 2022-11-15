package com.korzhuck.foosball.ui.rankings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingsViewModel @Inject constructor() : BaseRxViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Rankings Fragment"
    }
    val text: LiveData<String> = _text
}

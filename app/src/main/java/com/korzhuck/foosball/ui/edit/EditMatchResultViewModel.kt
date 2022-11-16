package com.korzhuck.foosball.ui.edit

import com.korzhuck.foosball.domain.usecase.RemoveResultUseCase
import com.korzhuck.foosball.domain.usecase.SaveResultUseCase
import com.korzhuck.foosball.utils.AppSchedulers
import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.ui.base.BaseRxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditMatchResultViewModel @Inject constructor(
    private val removeResult: RemoveResultUseCase,
    private val saveResult: SaveResultUseCase,
    private val schedulers: AppSchedulers,
) : BaseRxViewModel() {

    fun removeResult(matchResult: MatchResult?) {
        if (matchResult != null) {
            removeResult.invoke(matchResult)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe()
                .autoDispose()
        }
    }

    fun saveResult(matchResult: MatchResult?) {
        if (matchResult != null) {
            saveResult.invoke(matchResult)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe()
                .autoDispose()
        }
    }
}

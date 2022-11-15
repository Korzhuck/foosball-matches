package com.korzhuck.foosball.domain.usecase

import io.reactivex.rxjava3.core.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface LoadDataUseCase {
    operator fun invoke(): Completable
}

class LoadDataUseCaseImpl @Inject constructor() : LoadDataUseCase {
    override fun invoke(): Completable =
        Completable.timer(2, TimeUnit.SECONDS)
}

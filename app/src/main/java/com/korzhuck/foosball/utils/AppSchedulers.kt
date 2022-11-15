package com.korzhuck.foosball.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface AppSchedulers {
    fun io() : Scheduler

    fun main() : Scheduler
}

class BaseAppSchedulers @Inject constructor() : AppSchedulers {
    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}

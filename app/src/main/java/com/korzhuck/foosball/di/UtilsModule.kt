package com.korzhuck.foosball.di

import com.korzhuck.foosball.utils.AppSchedulers
import com.korzhuck.foosball.utils.BaseAppSchedulers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UtilsModule {
    @Binds
    fun bindSchedulers(
        schedulers: BaseAppSchedulers,
    ): AppSchedulers
}

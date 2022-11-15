package com.korzhuck.foosball.di

import com.korzhuck.foosball.utils.AppSchedulers
import com.korzhuck.foosball.utils.BaseAppSchedulers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {
    @Singleton
    @Binds
    fun bindSchedulers(
        schedulers: BaseAppSchedulers,
    ): AppSchedulers
}

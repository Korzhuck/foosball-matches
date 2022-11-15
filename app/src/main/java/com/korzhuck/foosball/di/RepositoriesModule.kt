package com.korzhuck.foosball.di

import com.korzhuck.foosball.data.repositories.ResultsRepositoryImpl
import com.korzhuck.foosball.domain.repositories.ResultsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Singleton
    @Binds
    fun bindResultsRepository(
        resultsRepositoryImpl: ResultsRepositoryImpl,
    ): ResultsRepository
}

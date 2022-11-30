package com.korzhuck.foosball.di

import com.korzhuck.foosball.data.source.InMemoryDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {
    @Singleton
    @Provides
    fun provideInMemoryDataSource() = InMemoryDataSource(
        matchResults = emptyList(),
    )
}

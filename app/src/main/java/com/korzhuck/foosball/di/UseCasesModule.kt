package com.korzhuck.foosball.di

import com.korzhuck.foosball.domain.usecase.GetAllRankingsUseCase
import com.korzhuck.foosball.domain.usecase.GetAllRankingsUseCaseImpl
import com.korzhuck.foosball.domain.usecase.GetAllResultsUseCase
import com.korzhuck.foosball.domain.usecase.GetAllResultsUseCaseImpl
import com.korzhuck.foosball.domain.usecase.LoadDataUseCase
import com.korzhuck.foosball.domain.usecase.LoadDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {
    @Binds
    fun bindLoadData(
        loadData: LoadDataUseCaseImpl,
    ): LoadDataUseCase

    @Binds
    fun bindGetAllResults(
        getAllResults: GetAllResultsUseCaseImpl,
    ): GetAllResultsUseCase

    @Binds
    fun bindGetAllRankings(
        getAllRankings: GetAllRankingsUseCaseImpl,
    ): GetAllRankingsUseCase
}

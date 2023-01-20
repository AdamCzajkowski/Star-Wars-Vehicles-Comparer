package com.adamczajkowski.domain.di

import com.adamczajkowski.domain.repository.IStarshipsRepository
import com.adamczajkowski.domain.repository.IStorageRepository
import com.adamczajkowski.domain.useCase.HistoryUseCase
import com.adamczajkowski.domain.useCase.StarshipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideStarshipUseCase(
        starshipsRepository: IStarshipsRepository
    ): StarshipsUseCase = StarshipsUseCase(starshipsRepository)

    @Singleton
    @Provides
    fun provideHistoryUseCase(
        storageRepository: IStorageRepository
    ): HistoryUseCase = HistoryUseCase(storageRepository)
}
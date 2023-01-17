package com.adamczajkowski.data.di

import com.adamczajkowski.data.repository.StarshipsRepository
import com.adamczajkowski.data.service.IStarshipsService
import com.adamczajkowski.domain.repository.IStarshipsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideStarshipsRepository(
        starshipsService: IStarshipsService
    ): IStarshipsRepository = StarshipsRepository(starshipsService)
}
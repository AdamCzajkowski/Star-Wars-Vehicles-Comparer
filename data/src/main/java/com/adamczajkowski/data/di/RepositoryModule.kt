package com.adamczajkowski.data.di

import com.adamczajkowski.data.database.dao.StarshipDao
import com.adamczajkowski.data.repository.StarshipsRepository
import com.adamczajkowski.data.repository.StorageRepository
import com.adamczajkowski.data.service.IStarshipsService
import com.adamczajkowski.domain.repository.IStarshipsRepository
import com.adamczajkowski.domain.repository.IStorageRepository
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

    @Singleton
    @Provides
    fun provideStorageRepository(
        starshipDao: StarshipDao
    ): IStorageRepository = StorageRepository(starshipDao)
}
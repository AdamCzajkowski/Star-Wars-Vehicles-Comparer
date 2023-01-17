package com.adamczajkowski.data.di

import com.adamczajkowski.data.api.StarWarsAPI
import com.adamczajkowski.data.service.IStarshipsService
import com.adamczajkowski.data.service.StarshipsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideStarshipService(
        starWarsAPI: StarWarsAPI
    ): IStarshipsService = StarshipsService(starWarsAPI)
}
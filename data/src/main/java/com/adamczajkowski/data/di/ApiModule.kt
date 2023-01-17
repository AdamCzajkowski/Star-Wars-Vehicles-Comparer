package com.adamczajkowski.data.di

import com.adamczajkowski.data.api.StarWarsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideStarWarsApi(
        retrofit: Retrofit
    ): StarWarsAPI = retrofit.create(StarWarsAPI::class.java)
}
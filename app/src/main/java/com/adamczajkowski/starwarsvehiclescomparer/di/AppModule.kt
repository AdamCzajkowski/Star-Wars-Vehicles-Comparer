package com.adamczajkowski.starwarsvehiclescomparer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class
    ]
)

@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context.applicationContext
    }
}
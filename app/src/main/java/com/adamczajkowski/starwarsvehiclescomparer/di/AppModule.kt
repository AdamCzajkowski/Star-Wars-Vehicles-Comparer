package com.adamczajkowski.starwarsvehiclescomparer.di

import android.content.Context
import com.adamczajkowski.common.di.SchedulerModule
import com.adamczajkowski.data.di.ApiModule
import com.adamczajkowski.data.di.RepositoryModule
import com.adamczajkowski.data.di.ServiceModule
import com.adamczajkowski.domain.di.UseCaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class,
        ApiModule::class,
        DatabaseModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        SchedulerModule::class
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
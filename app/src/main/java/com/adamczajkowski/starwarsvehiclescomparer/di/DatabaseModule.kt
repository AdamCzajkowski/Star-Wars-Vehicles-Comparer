package com.adamczajkowski.starwarsvehiclescomparer.di

import android.content.Context
import androidx.room.Room
import com.adamczajkowski.data.database.RoomDB
import com.adamczajkowski.data.database.dao.StarshipDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RoomDB = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        "database"
    ).build()

    @Provides
    @Singleton
    fun provideStarshipDao(
        roomDB: RoomDB
    ): StarshipDao = roomDB.starshipDao()
}
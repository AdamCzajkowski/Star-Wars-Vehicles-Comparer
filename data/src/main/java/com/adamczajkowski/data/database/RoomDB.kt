package com.adamczajkowski.data.database

import android.content.Context
import androidx.room.*
import com.adamczajkowski.data.database.converter.Converter
import com.adamczajkowski.data.database.entity.StarshipEntity

@Database(
    entities = [
        StarshipEntity::class
    ], version = 1
)
@TypeConverters(Converter::class)
abstract class RoomDB : RoomDatabase() {

    fun createDb(context: Context): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java, "database"
        ).build()
    }
}
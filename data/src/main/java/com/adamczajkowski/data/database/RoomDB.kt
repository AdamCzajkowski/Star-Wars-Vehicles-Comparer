package com.adamczajkowski.data.database

import androidx.room.*
import com.adamczajkowski.data.database.converter.Converter
import com.adamczajkowski.data.database.dao.StarshipDao
import com.adamczajkowski.data.database.entity.StarshipEntity

@Database(
    entities = [
        StarshipEntity::class
    ], version = 2
)
@TypeConverters(Converter::class)
abstract class RoomDB : RoomDatabase() {

    abstract fun starshipDao(): StarshipDao
}
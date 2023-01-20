package com.adamczajkowski.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "last_compared")
data class StarshipEntity(

    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String,

    @ColumnInfo(name = "compared_vehicles")
    val vehicles: String,

    @ColumnInfo(name = "added_data")
    var addedData: Date
)
package com.adamczajkowski.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adamczajkowski.common.models.StarshipComparisonHistoryItem
import java.util.*

@Entity(tableName = "last_compared")
data class StarshipEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "compared_vehicles")
    val vehicles: String = "",
    @ColumnInfo(name = "added_date")
    var addedDate: Date
)

fun StarshipEntity.toStarshipComparisonHistoryItem() = StarshipComparisonHistoryItem(
    vehicles, addedDate
)
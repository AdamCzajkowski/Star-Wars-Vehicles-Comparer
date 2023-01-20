package com.adamczajkowski.data.database.dao

import androidx.room.*
import com.adamczajkowski.data.database.entity.StarshipEntity

@Dao
interface StarshipDao {
    @Query("SELECT * FROM last_compared ORDER BY added_date DESC LIMIT 10")
    fun getLastTenDescending(): List<StarshipEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: StarshipEntity)

    @Delete
    fun delete(item: StarshipEntity)

    @Query("SELECT COUNT(*) FROM last_compared")
    fun getRowCount(): Int

    @Query("SELECT * FROM last_compared ORDER BY added_date ASC LIMIT 1")
    fun getLastlyAddedComparison(): StarshipEntity
}
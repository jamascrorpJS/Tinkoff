package com.jamascrorp.tinkoff.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamascrorp.tinkoff.data.database.models.pay.Subs

@Dao
interface SubsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(operations: List<Subs>)

    @Query("DELETE FROM item")
    suspend fun deleteAllItem()

    @Query("SELECT * FROM item")
    suspend fun getAllItem(): List<Subs>
}
package com.jamascrorp.tinkoff.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamascrorp.tinkoff.data.database.models.operations.OperationsModelDB

@Dao
interface OperationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOperations(operations: List<OperationsModelDB>)

    @Query("DELETE FROM operations")
    suspend fun deleteAllOperations()

    @Query("SELECT * FROM operations")
    suspend fun getAllOperations(): List<OperationsModelDB>
}
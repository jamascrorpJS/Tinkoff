package com.jamascrorp.tinkoff.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamascrorp.tinkoff.data.database.models.pay.PayModelItem


@Dao
interface PaysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePay(payModelDBItem: List<PayModelItem>)

    @Query("DELETE FROM pay_item")
    suspend fun deleteAllPays()

    @Query("SELECT * FROM pay_item")
    suspend fun getAllPays(): List<PayModelItem>
}
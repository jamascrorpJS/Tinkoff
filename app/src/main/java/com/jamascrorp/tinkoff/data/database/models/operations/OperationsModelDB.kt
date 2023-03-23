package com.jamascrorp.tinkoff.data.database.models.operations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "operations")
data class OperationsModelDB(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("icon")
    val icon: Int,
    @SerializedName("costName")
    val costName: String,
    @SerializedName("costType")
    val costType: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("priceType")
    val priceType: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("address")
    val address: String
)
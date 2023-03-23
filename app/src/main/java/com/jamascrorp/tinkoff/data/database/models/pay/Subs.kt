package com.jamascrorp.tinkoff.data.database.models.pay

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class Subs(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("address")
    val address: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("name")
    val name: String,
)
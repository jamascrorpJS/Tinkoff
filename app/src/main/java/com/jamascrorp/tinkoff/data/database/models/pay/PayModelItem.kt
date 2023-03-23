package com.jamascrorp.tinkoff.data.database.models.pay

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pay_item")
data class PayModelItem(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("category")
    val category: String,
    @SerializedName("icon")
    val icon: Int,
    @SerializedName("subs")
    val subs: List<Subs>,
)
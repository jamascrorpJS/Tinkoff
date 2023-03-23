package com.jamascrorp.tinkoff.data.database.models.bookmarks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookmarks")
data class BookmarksModelDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("image")
    val image: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("category")
    val category: String
)
package com.jamascrorp.tinkoff.data.database.models.pay

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromSubsList(value: List<Subs>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Subs>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSubsList(value: String): List<Subs> {
        val gson = Gson()
        val type = object : TypeToken<List<Subs>>() {}.type
        return gson.fromJson(value, type)
    }
}
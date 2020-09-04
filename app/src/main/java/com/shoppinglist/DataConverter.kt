package com.shoppinglist

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shoppinglist.database.ProductEntity

class DataConverter {

    @TypeConverter
    fun fromList(value: List<ProductEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<ProductEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
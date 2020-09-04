package com.shoppinglist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ListEntity(
    @ColumnInfo(name = "listName") val listName: String,
    @ColumnInfo(name = "productsAsJson") val products: String,
    @PrimaryKey(autoGenerate = true) val listId: Int? = null
): Serializable
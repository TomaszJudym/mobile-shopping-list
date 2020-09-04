package com.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListDao {

    @Query("SELECT * FROM listentity")
    fun getAllLists(): List<ListEntity>

    @Insert
    fun insertList(vararg lists: ListEntity)

    @Delete
    fun deleteList(listEntity: ListEntity)

    @Query("DELETE FROM listentity WHERE listId >= 0")
    fun clearDatabase()

}
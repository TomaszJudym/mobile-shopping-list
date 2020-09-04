package com.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class, ListEntity::class], version = 1, exportSchema = false)
abstract class ShoppingListDB: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun listDao(): ListDao

    companion object {
        var db: ShoppingListDB? = null

        fun createDatabase(context: Context): ShoppingListDB {
            if(db == null) {
                db = Room.databaseBuilder(context.applicationContext, ShoppingListDB::class.java, "shopping_db").allowMainThreadQueries().build()
            }
            return db as ShoppingListDB
        }
    }
}
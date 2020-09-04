package com.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM productentity")
    fun getAllProducts(): List<ProductEntity>

    @Insert
    fun insertProduct(vararg products: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Query("DELETE FROM productentity WHERE productId >= 0")
    fun clearDatabase()

    @Query("SELECT * FROM productentity WHERE name == :givenName")
    fun findProduct(givenName: String): ProductEntity?

}
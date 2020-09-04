package com.shoppinglist.userModule

import com.shoppinglist.database.ProductEntity

data class ListToDisplay (
    val name: String,
    val products: List<ProductEntity>
)
package com.shoppinglist

import androidx.lifecycle.MutableLiveData
import com.shoppinglist.database.ListEntity
import com.shoppinglist.database.ProductEntity

object ProductConfig {

    val lastAddedProduct = MutableLiveData<ProductEntity>().apply { value = ProductEntity("", 0) }

    val lastInteractedList = MutableLiveData<ListEntity>().apply { value = ListEntity("", "") }
    val productsList = mutableListOf<ProductEntity>()

}
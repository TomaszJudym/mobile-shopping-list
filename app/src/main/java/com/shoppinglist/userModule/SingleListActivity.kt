package com.shoppinglist.userModule


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoppinglist.DataConverter
import com.shoppinglist.ProductConfig

import com.shoppinglist.R
import com.shoppinglist.adminModule.ItemAdapter
import com.shoppinglist.database.ListEntity
import com.shoppinglist.database.ProductEntity
import com.shoppinglist.database.ShoppingListDB
import kotlinx.android.synthetic.main.fragment_single_list.*

class SingleListActivity : AppCompatActivity() {

    private val itemAdapter = ItemAdapter()
    private val dataConverter = DataConverter()
    private val db = ShoppingListDB.db!!
    private lateinit var listEntity: ListEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_single_list)

        listEntity = intent.extras!!.getSerializable("list") as ListEntity

        singleList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        val listToDisplay = dataConverter.toList(listEntity.products)

        listName.text = "List name: ${listEntity.listName}"
        totalCalories.text = "Total calories: ${calculateCalories(listToDisplay)}"
        itemAdapter.addList(listToDisplay)

        removeListButton.setOnClickListener {
            removeList()
        }
    }

    private fun removeList(){
        db.listDao().deleteList(listEntity)
        ProductConfig.lastInteractedList.value = listEntity
        finish()
    }

    private fun calculateCalories(list: List<ProductEntity>): String {
        var calories = 0
        list.forEach {
            calories += it.calories
        }
        return calories.toString()
    }

}

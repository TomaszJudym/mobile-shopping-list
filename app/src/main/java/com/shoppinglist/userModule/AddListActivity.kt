package com.shoppinglist.userModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoppinglist.DataConverter
import com.shoppinglist.ProductConfig
import com.shoppinglist.R
import com.shoppinglist.adminModule.ItemAdapter
import com.shoppinglist.database.ListEntity
import com.shoppinglist.database.ProductEntity
import com.shoppinglist.database.ShoppingListDB
import kotlinx.android.synthetic.main.activity_add_list.*

class AddListActivity : AppCompatActivity() {

    private val itemAdapter = ItemAdapter()
    private val db = ShoppingListDB.db!!
    private val dataConverter = DataConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        addedItemsToList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        addProductToListButton.setOnClickListener {
            addProduct()
        }

        confirmButton.setOnClickListener {
            confirmList()
        }

    }

    private fun confirmList(){
        if(ProductConfig.productsList.isNotEmpty()){
            if(enterListName.text.isNotEmpty()){

                val listName = enterListName.text.toString()
                val listItems = dataConverter.fromList(ProductConfig.productsList)
                val list = ListEntity(listName, listItems)

                db.listDao().insertList(list)

                //clear data after submitting list to database
                ProductConfig.productsList.clear()

                //submit list to product config for list update in user module acitivity
                ProductConfig.lastInteractedList.value = list

                //finish activity when everything is done
                finish()


            } else {
                Toast.makeText(this, "You have to enter list's name.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Your list is empty.", Toast.LENGTH_LONG).show()
        }
    }

    private fun addProduct(){
        if(productName.text.isNotEmpty()){

            val name = productName.text.toString()
            val product: ProductEntity? = db.productDao().findProduct(name)

            if(product != null) {
                ProductConfig.productsList.add(product)
                itemAdapter.addList(ProductConfig.productsList)
            } else {
                ProductConfig.productsList.add(ProductEntity(name, 0))
                itemAdapter.addList(ProductConfig.productsList)
            }

        } else {
            Toast.makeText(this, "You have to enter product name.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ProductConfig.productsList.clear()
    }
}

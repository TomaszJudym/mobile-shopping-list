package com.shoppinglist.adminModule


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shoppinglist.ProductConfig

import com.shoppinglist.R
import com.shoppinglist.database.ProductEntity
import com.shoppinglist.database.ShoppingListDB
import kotlinx.android.synthetic.main.fragment_add_product.*

class AddProductActivity : AppCompatActivity() {

    val db = ShoppingListDB.db!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_product)

        addProductButton.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        if (name.text.isNotEmpty() && calories.text.isNotEmpty()) {
            val name = name.text.toString()
            val calories = calories.text.toString().toInt()
            val product = ProductEntity(name, calories)
            db.productDao().insertProduct(product)
            ProductConfig.lastAddedProduct.value = product
            finish()
        } else {
            Toast.makeText(this, "You have to enter text to both edit texts.", Toast.LENGTH_LONG).show()
        }
    }
}

package com.shoppinglist.adminModule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoppinglist.ProductConfig
import com.shoppinglist.R
import com.shoppinglist.database.ShoppingListDB
import kotlinx.android.synthetic.main.fragment_admin_module.*


class AdminModuleActivity : AppCompatActivity() {

    private val itemAdapter = ItemAdapter()
    private val db = ShoppingListDB.db!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_admin_module)

        addItemsButton.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        avaibleItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        ProductConfig.lastAddedProduct.observe(this, Observer {
            val items = db.productDao().getAllProducts()
            itemAdapter.addList(items)
        })

    }


}

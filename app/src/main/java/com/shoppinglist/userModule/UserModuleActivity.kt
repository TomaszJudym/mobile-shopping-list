package com.shoppinglist.userModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoppinglist.DataConverter
import com.shoppinglist.ProductConfig
import com.shoppinglist.R
import com.shoppinglist.database.ListEntity
import com.shoppinglist.database.ShoppingListDB
import kotlinx.android.synthetic.main.activity_user_module.*

class UserModuleActivity : AppCompatActivity() {

    private val listsAdapter = ListAdapter{onItemClicked(it)}
    private val dataConverter = DataConverter()
    private val db = ShoppingListDB.db!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_module)

        avaibleLists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listsAdapter
        }

        addListButton.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
        }

        ProductConfig.lastInteractedList.observe(this, Observer {
            val lists = db.listDao().getAllLists()
            listsAdapter.addList(lists)
        })

    }

    override fun onPause() {
        super.onPause()
        addListButton.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        addListButton.visibility = View.VISIBLE
    }

    private fun onItemClicked(listEntity: ListEntity){
        val intent = Intent(this, SingleListActivity::class.java).apply {
            putExtra("list", listEntity)
        }
        startActivity(intent)
    }
}

package com.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shoppinglist.adminModule.AdminModuleActivity
import com.shoppinglist.database.ShoppingListDB
import com.shoppinglist.userModule.UserModuleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShoppingListDB.createDatabase(applicationContext)

        adminButton.setOnClickListener {
            Log.i("supertest123", "button clicked")
            val intent = Intent(this, AdminModuleActivity::class.java)
            startActivity(intent)
        }

        userButton.setOnClickListener {
            val intent = Intent(this, UserModuleActivity::class.java)
            startActivity(intent)
        }

    }
}

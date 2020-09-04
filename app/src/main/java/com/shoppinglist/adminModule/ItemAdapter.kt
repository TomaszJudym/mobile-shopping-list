package com.shoppinglist.adminModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoppinglist.R
import com.shoppinglist.database.ProductEntity
import kotlinx.android.synthetic.main.avaible_item.view.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.avaible_item, parent, false)
        return ItemHolder(inflatedView)
    }

    private val products = mutableListOf<ProductEntity>()

    fun addList(listToAdd: List<ProductEntity>) {
        if (products.isNotEmpty())
            products.clear()
        products.addAll(listToAdd)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(products[position])
    }

    class ItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: ProductEntity) {
            view.itemName.text = "${product.name} : ${product.calories}"
        }
    }
}
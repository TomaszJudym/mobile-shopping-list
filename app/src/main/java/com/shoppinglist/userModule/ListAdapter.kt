package com.shoppinglist.userModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoppinglist.R
import com.shoppinglist.database.ListEntity
import com.shoppinglist.database.ProductEntity
import kotlinx.android.synthetic.main.avaible_item.view.*

class ListAdapter(private val listener: (ListEntity) -> Unit) : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.avaible_item, parent, false)
        return ListHolder(inflatedView, listener)
    }

    private val lists = mutableListOf<ListEntity>()

    fun addList(listToAdd: List<ListEntity>) {
        if (lists.isNotEmpty())
            lists.clear()
        lists.addAll(listToAdd)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(lists[position])
    }

    class ListHolder(val view: View, private val itemClick: (ListEntity) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(list: ListEntity) {
            view.itemName.text = list.listName
            view.setOnClickListener {
                itemClick(list)
            }
        }
    }
}
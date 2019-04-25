package com.alifjafar.mynews.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<ViewItem<RecyclerView.ViewHolder>> = ArrayList()
    private val holderCreatorMap: HashMap<Int, (parent: ViewGroup) -> RecyclerView.ViewHolder> = HashMap()

    fun addItems(viewItem: ViewItem<*>) {
        items.add(viewItem as ViewItem<RecyclerView.ViewHolder>)
        holderCreatorMap[viewItem.getViewType()] = { viewItem.createHolder(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        holderCreatorMap[viewType]!!.invoke(parent)

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindHolder(holder)
    }

    fun clear() {
        items.clear()
    }

}
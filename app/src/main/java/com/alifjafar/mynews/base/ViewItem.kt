package com.alifjafar.mynews.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ViewItem<T : RecyclerView.ViewHolder> {
    abstract fun getViewType() : Int
    abstract fun createHolder(parent: ViewGroup) : T
    abstract fun bindHolder(holder: T)
}
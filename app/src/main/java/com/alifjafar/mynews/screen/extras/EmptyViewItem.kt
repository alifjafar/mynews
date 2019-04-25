package com.alifjafar.mynews.screen.extras

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alifjafar.mynews.base.ViewItem

class EmptyViewItem : ViewItem<EmptyViewItem.Holder>() {
    override fun getViewType(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createHolder(parent: ViewGroup): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindHolder(holder: Holder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
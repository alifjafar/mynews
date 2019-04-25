package com.alifjafar.mynews.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alifjafar.mynews.R
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseRecyclerViewFragment<T : StatedViewModel<*>, S> : BaseStatedFragment<T, S>() {
    var adapter: BaseRecyclerViewAdapter = BaseRecyclerViewAdapter()

    override fun getLayout() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.also {
            it?.layoutManager = LinearLayoutManager(context)
            it?.adapter = adapter
        }
    }

    override fun render(state: S) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
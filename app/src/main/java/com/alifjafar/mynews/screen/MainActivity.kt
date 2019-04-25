package com.alifjafar.mynews.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.alifjafar.mynews.R
import com.alifjafar.mynews.base.BaseRecyclerViewAdapter
import com.alifjafar.mynews.models.Article

class MainActivity : AppCompatActivity() {

    @BindView(R.id.recycler_item)
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BaseRecyclerViewAdapter
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        context = this
        adapter = BaseRecyclerViewAdapter()
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        init()
    }

    fun init() {
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getState().observe(this, Observer { t ->
            adapter.clear()
            when (t.status) {
                MainViewModel.Status.SUCCESS -> renderItem(t.topHeadline)
                else -> Toast.makeText(context, "Gagal Load Article", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()
        })
        viewModel.doGetTopHeadlines("id")
    }

    fun renderItem(articles: ArrayList<Article>) {
        if (!articles.isEmpty()) {
            articles.forEach {
                adapter.addItems(MainItemView(it))
            }
        }
    }
}

package com.alifjafar.mynews.screen.article

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alifjafar.mynews.base.BaseRecyclerViewFragment
import com.alifjafar.mynews.models.Article
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseArticleFragment :
    BaseRecyclerViewFragment<ArticleViewModel, ArticleViewModel.State>() {
    override var viewModelClass = ArticleViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh.setOnRefreshListener { viewModel.doGetArticle() }
    }

    override fun render(state: ArticleViewModel.State) {
        adapter.clear()
        when (state.isLoading) {
            true -> swipeRefresh.isRefreshing = true
            else -> swipeRefresh.isRefreshing = false
        }

        renderItem(state.topHeadline)
        renderStatus(state.status)
        adapter.notifyDataSetChanged()
    }

    private fun renderItem(articles: ArrayList<Article>) {
        if (!articles.isEmpty()) {
            articles.forEach {
                adapter.addItems(ArticleItemView(it))
            }
        }
    }

    private fun renderStatus(status : ArticleViewModel.Status) {
        if(status == ArticleViewModel.Status.ERROR)
            Toast.makeText(context, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show()
    }
}
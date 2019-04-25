package com.alifjafar.mynews.screen.article.fragment

import android.os.Bundle
import android.view.View
import com.alifjafar.mynews.network.NewsApiService
import com.alifjafar.mynews.screen.article.BaseArticleFragment

class EverythingFragment : BaseArticleFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticle = {
            NewsApiService.create().getEverything("technology")
        }

        viewModel.doGetArticle()
    }
}
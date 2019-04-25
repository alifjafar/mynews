package com.alifjafar.mynews.screen.article.detail

import com.alifjafar.mynews.base.StatedViewModel
import com.alifjafar.mynews.models.Article

class DetailViewModel : StatedViewModel<DetailViewModel.State>() {
    override fun initState() = State()

    class State {
        var article : Article? = null
    }

    fun getArticle(article: Article) {
        setState {
            this.article = article
        }
    }
}
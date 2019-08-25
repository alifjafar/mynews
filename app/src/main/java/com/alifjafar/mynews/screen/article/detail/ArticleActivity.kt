package com.alifjafar.mynews.screen.article.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alifjafar.mynews.R
import com.alifjafar.mynews.models.Article
import com.alifjafar.mynews.utils.ArticleUtil
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar_article)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getArticle(intent.getParcelableExtra("article"))
        viewModel.getState().observe(this, Observer {
            renderArticle(it.article)
        })

        var isShow: Boolean
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, i ->
            isShow = true
            var scrollRange = -1

            if (scrollRange == -1) scrollRange = appBar.totalScrollRange
            if (scrollRange + i == 0) {
                collapsing_toolbar.title = article_title.text
                toolbar_article.setBackgroundColor(ContextCompat.getColor(applicationContext, android.R.color.transparent))
                isShow = true
            } else if (isShow) {
                collapsing_toolbar.title = " "
                toolbar_article.setBackgroundResource(R.drawable.gradient_black_to_white)
                isShow = false
            }

        })
    }

    private fun renderArticle(article: Article?) {
        article_title.text = article?.title
        article_content.text = article?.content ?: (article?.description ?: article?.title)
        article_date.text = ArticleUtil().formatDate(article?.publishedAt)
        author.text = article?.author ?: article?.source?.name
        Glide.with(this).load(
            if (!article?.urlToImage.isNullOrEmpty()) article?.urlToImage
            else ContextCompat.getDrawable(applicationContext, R.drawable.ic_image_placeholder_120dp)
        ).optionalFitCenter().into(imageView)
        val source: String =  getString(R.string.source) + article?.source?.name
        article_source.text = source
        btn_read_more.setOnClickListener {
            val intent = Intent(applicationContext, WebView::class.java)
            intent.putExtra("url", article?.url)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

package com.alifjafar.mynews.screen.article.detail

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alifjafar.mynews.R
import com.alifjafar.mynews.models.Article
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setupWindowAnimation()
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

            if (scrollRange == -1) {
                scrollRange = appBar.totalScrollRange
            }
            if (scrollRange + i == 0) {
                collapsing_toolbar.title = article_title.text
                isShow = true
            } else if (isShow) {
                collapsing_toolbar.title = " "
                isShow = false
            }

        })
    }

    private fun renderArticle(article: Article?) {
        article_title.text = article?.title
        article_content.text = article?.content
        article_date.text = article?.publishedAt
        author.text = article?.author
        Glide.with(this).load(article?.urlToImage).into(imageView)
        article_source.text = "Sumber : " + article?.source?.name
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun setupWindowAnimation() {
        val fade = Fade()
        fade.duration = 1000
        window.enterTransition = fade
    }
}

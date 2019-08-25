package com.alifjafar.mynews.screen.article

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alifjafar.mynews.R
import com.alifjafar.mynews.base.ViewItem
import com.alifjafar.mynews.models.Article
import com.alifjafar.mynews.screen.article.detail.ArticleActivity
import com.alifjafar.mynews.utils.ArticleUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_articles.view.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class ArticleItemView(private val article: Article) : ViewItem<ArticleItemView.Holder>() {

    override fun getViewType(): Int {
        return ArticleItemView::class.hashCode()
    }

    override fun createHolder(parent: ViewGroup): Holder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_articles,
                parent,
                false
            )
        )
    }

    override fun bindHolder(holder: Holder) {
        holder.bind(article)
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) = with(itemView) {
            article_title.text = ArticleUtil().makeExcerpt(article.title, 80)
            Glide.with(itemView).load(
                if (!article.urlToImage.isNullOrEmpty()) article.urlToImage
                else ContextCompat.getDrawable(context, R.drawable.ic_image_placeholder_120dp)
            ).centerCrop().into(img_article)
            text_desc.text =
                if (!article.description.isNullOrEmpty()) ArticleUtil().makeExcerpt(article.description, 100)
                else article.title
            author.text =
                if (article.author.isNullOrEmpty()) article.source.name
                else article.author
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ArticleActivity::class.java)
                intent.putExtra("article", article)
                itemView.context.startActivity(intent)
            }
        }
    }
}
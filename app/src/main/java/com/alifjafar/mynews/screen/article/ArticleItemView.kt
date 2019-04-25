package com.alifjafar.mynews.screen.article

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alifjafar.mynews.R
import com.alifjafar.mynews.base.ViewItem
import com.alifjafar.mynews.models.Article
import com.alifjafar.mynews.screen.article.detail.ArticleActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_articles.view.*

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
            article_title.text = if(article.title.length > 80) article.title.substring(0,80) + " ..."
            else article.title
            Glide.with(itemView).load(article.urlToImage).centerCrop().into(img_article)
            text_desc.text = if (article.description.length > 100) article.description.substring(0, 100)
            else article.description
            author.text = if (article.author.isNullOrEmpty()) context.getString(R.string.anonymouse)
            else article.author

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, ArticleActivity::class.java)
                intent.putExtra("article", article)
                itemView.context.startActivity(intent)
            }
        }
    }
}
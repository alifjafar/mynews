package com.alifjafar.mynews.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alifjafar.mynews.App
import com.alifjafar.mynews.R
import com.alifjafar.mynews.base.ViewItem
import com.alifjafar.mynews.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_articles.view.*

class MainItemView(val article: Article) : ViewItem<MainItemView.Holder>() {

    override fun getViewType(): Int {
        return 1001
    }

    override fun createHolder(parent: ViewGroup): Holder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.item_articles, parent, false))
    }

    override fun bindHolder(holder: Holder) {
        holder.bind(article)
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) = with(itemView) {
            text_title.text = article.title
            Glide.with(itemView).load(article.urlToImage).centerCrop().into(img_article)
            text_desc.text = if (article.description.length > 100) article.description.substring(0, 100)
            else article.description
            text_author.text = if (article.author.isNullOrEmpty()) context.getString(R.string.anonymouse)
            else article.author
        }
    }
}
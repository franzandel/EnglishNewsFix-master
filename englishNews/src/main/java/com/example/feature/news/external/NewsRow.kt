package com.example.feature.news.external

import com.example.feature.R
import com.example.feature.news.data.response.Article
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.news_row.view.*

class NewsRow(val article: Article): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.news_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (article.source?.id.equals(null)) {
            val id = article.source?.name?.substringBefore('.')
            viewHolder.itemView.textView_username_News?.text = id
        } else
            viewHolder.itemView.textView_username_News?.text = article.source?.id

        viewHolder.itemView.textView_latestMessage_News?.text = article.source?.name
    }
}
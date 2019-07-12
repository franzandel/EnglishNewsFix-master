package com.example.feature.articles.presentation.activity.contract

import com.example.feature.news.data.response.News
import com.example.base.presentation.view.BaseView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class ArticlesContract {
    interface View : BaseView {
        fun setAdapter(articlesAdapter: GroupAdapter<ViewHolder>)
    }

    interface UserActionListener{
        fun setIntentParameters(news: News, sourceName: String)
        fun setAdapterValue(articlesAdapter: GroupAdapter<ViewHolder>)
        fun resetAdapter(articlesAdapter: GroupAdapter<ViewHolder>)
        fun searchData(query: String, articlesAdapter: GroupAdapter<ViewHolder>)
    }
}
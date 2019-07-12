package com.example.feature.news.presentation.activity.contract

import com.example.feature.news.data.response.News
import com.example.base.presentation.view.BaseView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class NewsContract {
    interface View : BaseView {
        fun showProgressBar()
        fun hideProgressBar()
        fun showError(msg: String)
        fun setAdapterParameter(news: News)
        fun setAdapter(news: News, newsAdapter: GroupAdapter<ViewHolder>)
    }

    interface UserActionListener{
        fun fetchDataFromApi()
        fun onSuccessFetchData(news: News)
        fun onFailedFetchData(errMsg: String?)
        fun setAdapterValue(news: News, newsAdapter: GroupAdapter<ViewHolder>)
    }

    interface Router {
        fun goToArticlesPage(sourceName: String?, news: News)
    }
}
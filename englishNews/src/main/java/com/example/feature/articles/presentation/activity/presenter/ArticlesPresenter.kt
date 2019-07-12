package com.example.feature.articles.presentation.activity.presenter

import com.example.feature.articles.presentation.activity.contract.ArticlesContract
import com.example.feature.news.data.response.News
import com.example.base.scheduler.SchedulerProvider
import com.example.feature.articles.external.ArticlesRow
import com.example.base.presentation.presenter.BasePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class ArticlesPresenter
@Inject constructor(schedulerProvider: SchedulerProvider)
    : BasePresenter<ArticlesContract.View>(schedulerProvider),
    ArticlesContract.UserActionListener {

    lateinit var news: News
    lateinit var sourceName: String

    override fun setIntentParameters(news: News, sourceName: String) {
        this.news = news
        this.sourceName = sourceName
    }

    override fun setAdapterValue(articlesAdapter: GroupAdapter<ViewHolder>) {
        news.articles.forEach {article ->
            if (article.source?.name == sourceName)
                articlesAdapter.add(ArticlesRow(article))
        }

        view?.setAdapter(articlesAdapter)
    }

    override fun resetAdapter(articlesAdapter: GroupAdapter<ViewHolder>) {
        articlesAdapter.clear()
        setAdapterValue(articlesAdapter)
    }

    override fun searchData(query: String, articlesAdapter: GroupAdapter<ViewHolder>) {
        articlesAdapter.clear()

        news.articles.forEach {article ->
            val title = article.title.toString()
            val description = article.description.toString()

            if (title.contains(query, ignoreCase = true) ||
                description.contains(query, ignoreCase = true)) {

                if (article.source?.name == sourceName)
                    articlesAdapter.add(ArticlesRow(article))
            }
        }

        view?.setAdapter(articlesAdapter)
    }
}
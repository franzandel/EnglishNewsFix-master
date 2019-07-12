package com.example.feature.news.presentation.activity.presenter

import com.example.feature.news.data.response.News
import com.example.base.scheduler.SchedulerProvider
import com.example.feature.news.presentation.activity.contract.NewsContract
import com.example.feature.news.external.NewsRow
import com.example.base.presentation.presenter.BasePresenter
import com.example.feature.news.domain.usecase.NewsUseCase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class NewsPresenter
@Inject constructor(private val newsUseCase: NewsUseCase,
                    schedulerProvider: SchedulerProvider
                    ) : BasePresenter<NewsContract.View>(schedulerProvider),
    NewsContract.UserActionListener {

    override fun fetchDataFromApi() {
        view?.showProgressBar()
        addDisposable(
            newsUseCase.getNewsRepo(newsUseCase.requestAPIKey())
                .subscribe ({ news ->
                    onSuccessFetchData(news)
                }, { err ->
                    onFailedFetchData(err.message)
                })
        )
    }

    override fun onSuccessFetchData(news: News) {
        view?.setAdapterParameter(news)
        view?.hideProgressBar()
    }

    override fun onFailedFetchData(errMsg: String?) {
        view?.showError(errMsg!!)
        view?.hideProgressBar()
    }

    override fun setAdapterValue(news: News, newsAdapter: GroupAdapter<ViewHolder>) {
        val articlesCopy = news.articles.map {article ->
            NewsRow(article)
        }

        val distinctArticles = articlesCopy.distinctBy {newsRow ->
            newsRow.article.source?.name
        }

        newsAdapter.addAll(distinctArticles)
        view?.setAdapter(news, newsAdapter)
    }
}
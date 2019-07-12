package com.example.feature.news.data.repository

import com.example.feature.news.data.response.News
import com.example.feature.news.data.network.NewsNetworkService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDataStore
@Inject constructor(private var newsNetworkService: NewsNetworkService
                    ) : NewsRepository{

    /**
     * Get News Data
     * @Param apiKey
     */
    override fun getNewsRepo(apiKey: String): Flowable<News> {
        return newsNetworkService.getAllNews(apiKey)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun requestAPIKey(): String {
        return NewsNetworkService.API_KEY
    }
}

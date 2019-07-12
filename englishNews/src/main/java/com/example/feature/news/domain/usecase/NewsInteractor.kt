package com.example.feature.news.domain.usecase

import com.example.feature.news.data.response.News
import com.example.feature.news.data.repository.NewsDataStore
import io.reactivex.Flowable
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsDataStore: NewsDataStore) : NewsUseCase {
    override fun getNewsRepo(apiKey: String): Flowable<News> {
        return newsDataStore.getNewsRepo(apiKey)
    }

    override fun requestAPIKey(): String {
        return newsDataStore.requestAPIKey()
    }


}
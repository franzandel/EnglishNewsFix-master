package com.example.feature.news.domain.usecase

import com.example.feature.news.data.response.News
import io.reactivex.Flowable

interface NewsUseCase {
    fun getNewsRepo(apiKey: String): Flowable<News>
    fun requestAPIKey(): String
}
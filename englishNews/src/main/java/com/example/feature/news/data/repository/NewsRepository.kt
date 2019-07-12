package com.example.feature.news.data.repository

import com.example.feature.news.data.response.News
import io.reactivex.Flowable

interface NewsRepository {
    fun getNewsRepo(apiKey: String): Flowable<News>

    fun requestAPIKey(): String
}
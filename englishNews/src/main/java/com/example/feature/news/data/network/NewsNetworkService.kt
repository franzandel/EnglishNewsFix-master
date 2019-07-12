package com.example.feature.news.data.network

import com.example.feature.news.data.response.News
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsNetworkService {
    companion object {
        const val API_KEY = "f79d6965f8814037b1412eb6451944ba"
    }

    @GET("/v2/top-headlines?country=us&category=business")
    fun getAllNews(@Query("apiKey") apiKey: String): Flowable<News>
}

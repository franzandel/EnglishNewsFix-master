package com.example.feature.news.di.module

import com.example.base.di.module.BaseNetworkModule
import com.example.base.di.scope.ActivityScope
import com.example.feature.news.data.network.NewsNetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class NewsNetworkModule : BaseNetworkModule() {

    @Provides
    @ActivityScope
    fun provideNewsNetworkService(@Named("news_rest") retrofit: Retrofit): NewsNetworkService =
        retrofit.create(NewsNetworkService::class.java)
}
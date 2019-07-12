package com.example.feature.news.di.module

import android.content.Context
import com.example.feature.news.data.repository.NewsDataStore
import com.example.feature.news.domain.router.NewsRouter
import com.example.base.di.scope.ActivityScope
import com.example.base.scheduler.AppSchedulerProvider
import com.example.feature.news.presentation.activity.presenter.NewsPresenter
import com.example.base.presentation.router.ScreenRouter
import com.example.feature.news.data.network.NewsNetworkService
import com.example.feature.news.domain.usecase.NewsInteractor
import com.example.feature.news.domain.usecase.NewsUseCase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.Module
import dagger.Provides

@Module
class NewsModule {
    @Provides
    @ActivityScope
    fun provideNewsPresenter(newsUseCase: NewsUseCase,
                             appSchedulerProvider: AppSchedulerProvider
    ): NewsPresenter {
        return NewsPresenter(
            newsUseCase,
            appSchedulerProvider
        )
    }

    @Provides
    @ActivityScope
    fun provideNewsUseCase(newsDataStore: NewsDataStore): NewsUseCase = NewsInteractor(newsDataStore)

    @Provides
    @ActivityScope
    fun provideNewsDataStore(newsNetworkService: NewsNetworkService): NewsDataStore = NewsDataStore(newsNetworkService)

    @Provides
    @ActivityScope
    fun provideNewsAdapter(): GroupAdapter<ViewHolder> {
        return GroupAdapter()
    }

    @Provides
    @ActivityScope
    fun provideNewsRouter(mRouter: ScreenRouter,
                          mContext: Context
                         ): NewsRouter {
        return NewsRouter(mRouter, mContext)
    }
}
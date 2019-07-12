package com.example.feature.news.domain.router

import android.content.Context
import android.content.Intent
import com.example.feature.news.data.response.News
import com.example.feature.news.presentation.activity.contract.NewsContract
import com.example.feature.news.presentation.activity.view.NewsActivity.Companion.NEWS_KEY
import com.example.feature.news.presentation.activity.view.NewsActivity.Companion.SOURCE_NAME_KEY
import com.example.base.presentation.router.ScreenRouter
import javax.inject.Inject

class NewsRouter @Inject constructor(
    private val mRouter: ScreenRouter,
    private val mContext: Context
) : NewsContract.Router {

    override fun goToArticlesPage(sourceName: String?, news: News) {
        mRouter.getScreenIntent(mContext, ScreenRouter.ActivityScreen.Articles.ArticlesScreen)?.run {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(SOURCE_NAME_KEY, sourceName)
            putExtra(NEWS_KEY, news)
            mContext.startActivity(this)
        }
    }
}
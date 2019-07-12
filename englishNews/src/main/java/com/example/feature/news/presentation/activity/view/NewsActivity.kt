package com.example.feature.news.presentation.activity.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.feature.news.data.response.News
import com.example.feature.news.domain.router.NewsRouter
import com.example.feature.news.presentation.activity.contract.NewsContract
import com.example.feature.news.presentation.activity.presenter.NewsPresenter
import com.example.feature.news.external.NewsRow
import com.example.base.presentation.activity.BaseActivity
import com.example.feature.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import javax.inject.Inject

class NewsActivity : BaseActivity(),
    NewsContract.View {
    companion object {
        const val NEWS_KEY = "NEWS"
        const val SOURCE_NAME_KEY = "SOURCE_NAME"
    }

    @Inject
    lateinit var mNewsPresenter: NewsPresenter
    @Inject
    lateinit var newsAdapter: GroupAdapter<ViewHolder>
    @Inject
    lateinit var newsRouter: NewsRouter

    override fun getLayoutId(): Int {
        return R.layout.activity_news
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
    }

    override fun showProgressBar() {
        avNewsLoadingIndicator?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        avNewsLoadingIndicator?.visibility = View.GONE
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun setAdapterParameter(news: News) {
        mNewsPresenter.setAdapterValue(news, newsAdapter)
    }

    override fun setAdapter(news: News, newsAdapter: GroupAdapter<ViewHolder>) {
        recyclerView_news.adapter = newsAdapter

        newsAdapter.setOnItemClickListener { item, _->
            val newsRow = item as NewsRow
            newsRouter.goToArticlesPage(newsRow.article.source?.name, news)
        }
    }

    override fun onDestroy() {
        mNewsPresenter.detachView()
        super.onDestroy()
    }

    /**
     * Setup UI in the first Time when this activity Load
     * */

    private fun setupUI() {
        setupToolbar()
        mNewsPresenter.attachView(this)
        setupRecyclerView()
        mNewsPresenter.fetchDataFromApi()
    }

    /**
     * Setup Toolbar for title
     * */

    private fun setupToolbar() {
        //Setup
        tvToolbarTitle.text = resources.getString(R.string.news_toolbar_title)
    }

    private fun setupRecyclerView() {
        recyclerView_news.addItemDecoration(
            DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL)
        )
    }
}

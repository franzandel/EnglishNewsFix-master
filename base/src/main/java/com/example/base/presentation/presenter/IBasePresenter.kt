package com.example.base.presentation.presenter

import com.example.base.presentation.view.BaseView

interface IBasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}
package com.example.base.presentation.view

import com.example.base.presentation.presenter.BasePresenter

interface BaseView{
    fun setPresenter(presenter: BasePresenter<*>)
}
package com.example.base.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.base.presentation.presenter.BasePresenter
import com.example.base.presentation.view.BaseView
import dagger.android.AndroidInjection


/**
 * Abstract class for handling all activities
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var presenter: BasePresenter<*>? = null

    /**
     * This function replace onCreate as main function run in activity
     * Auto Dependency Injection
     * @param savedInstanceState
     * */

    abstract fun onActivityReady(savedInstanceState: Bundle?)

    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        //Auto DI
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        onActivityReady(savedInstanceState)
    }

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }
}
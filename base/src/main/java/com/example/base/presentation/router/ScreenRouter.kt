package com.example.base.presentation.router

import android.content.Context
import android.content.Intent

interface ScreenRouter {

    sealed class ActivityScreen {

        class Articles {
            object ArticlesScreen : ActivityScreen()
        }

    }

    fun getScreenIntent(context: Context, screen: ActivityScreen): Intent?
}
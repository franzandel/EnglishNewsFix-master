package com.example.englishnewsfix.app

import android.app.Activity
import android.app.Application
import com.example.englishnewsfix.app.di.component.AppComponent
import com.example.englishnewsfix.app.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class EnglishNewsApp : Application(), HasActivityInjector {
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        //Create App Component
        appComponent = createComponent()
        appComponent.inject(this)
    }

    /**
     * Initialize Dependency Injection With Dagger
     * Level DI Application
     * */


    private fun createComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
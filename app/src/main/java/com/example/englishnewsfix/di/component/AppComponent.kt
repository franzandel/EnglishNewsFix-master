package com.example.englishnewsfix.app.di.component

import android.app.Application
import com.example.englishnewsfix.app.EnglishNewsApp
import com.example.englishnewsfix.app.di.module.AppModule
import com.example.englishnewsfix.app.di.module.builder.ActivityBuilder
import com.example.base.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(modules = [(AndroidInjectionModule::class),
                      (AppModule::class),
                      (ActivityBuilder::class)
                     ])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: EnglishNewsApp)
}
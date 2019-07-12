package com.example.base.di.module

import com.example.base.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
abstract class BaseNetworkModule {
    companion object {
        const val baseUrl = "https://newsapi.org"
    }

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(loggingInterceptor)
        }

        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Named("news_rest")
    fun provideRestClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        builder.client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return builder.build()
    }

}
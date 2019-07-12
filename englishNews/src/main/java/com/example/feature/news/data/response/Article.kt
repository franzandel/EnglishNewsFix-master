package com.example.feature.news.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val author: String ?= null,
    val content: String ?= null,
    val description: String ?= null,
    val publishedAt: String ?= null,
    val source: Source?= null,
    val title: String ?= null,
    val url: String ?= null,
    val urlToImage: String ?= null
) : Parcelable
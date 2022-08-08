package com.example.onlinepractice.network

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: List<Article> = listOf()
)
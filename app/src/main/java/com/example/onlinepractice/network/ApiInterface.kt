package com.example.onlinepractice.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&apiKey=6d70e376552d4c71a6cbdd9eb59791cf
//https://newsapi.org/v2/everything?q=bitcoin&apiKey=6d70e376552d4c71a6cbdd9eb59791cf

interface ApiInterface {
    @GET("v2/everything")
    fun getEverything(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Call<News>?

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Call<News>?
}
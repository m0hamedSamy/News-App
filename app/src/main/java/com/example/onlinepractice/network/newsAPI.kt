package com.example.onlinepractice.network

import com.example.onlinepractice.homePage.Item
import retrofit2.Call

interface newsAPI {
    fun news(): Call<List<Item>>
}
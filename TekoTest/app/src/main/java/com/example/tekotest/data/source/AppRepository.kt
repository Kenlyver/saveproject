package com.example.tekotest.data.source

import com.example.tekotest.data.database.retrofit.RetrofitService

class AppRepository(private val retrofitService: RetrofitService?) {
    suspend fun getAllProducts() = retrofitService?.getAllProducts()
    suspend fun getColors() = retrofitService?.getColor()
}
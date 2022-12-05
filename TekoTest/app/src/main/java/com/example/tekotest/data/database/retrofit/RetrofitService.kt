package com.example.tekotest.data.database.retrofit

import com.example.tekotest.data.models.Color
import com.example.tekotest.data.models.Product
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("colors")
    suspend fun getColor(): Response<List<Color>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService? {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://hiring-test.stag.tekoapis.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService
        }
    }
}
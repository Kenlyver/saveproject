package com.example.loginmvp.API

import com.example.loginmvp.Model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface api {
    @FormUrlEncoded
    @POST("c95efa2b-66e6-4aa1-87cd-cd1a31060ad2")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<LoginResponse>
}
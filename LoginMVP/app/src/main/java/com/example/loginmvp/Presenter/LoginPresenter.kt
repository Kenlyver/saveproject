package com.example.loginmvp.Presenter

import android.util.Log
import com.example.loginmvp.API.RetrofitClient
import com.example.loginmvp.Model.LoginResponse
import com.example.loginmvp.Model.User
import com.example.loginmvp.View.ILoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(internal val iLoginView: ILoginView) : ILoginPresenter {
    override fun onLogin(email: String, password: String) {
        val user = User(email, password)
        val loginCode = user.isDataValid()
        if (loginCode == 0)
            iLoginView.onLoginFail("Email not be null")
        else if (loginCode == 1)
            iLoginView.onLoginFail("Wrong email address")
        else if (loginCode == 2)
            iLoginView.onLoginFail("Password must >6 ")
        else loginRetrofit(user.email, user.password)

    }

    fun loginRetrofit(email: String, password: String) {
        RetrofitClient.instance.userLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("Check0", t.message.toString())
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (!response.body()?.error!!) {
                        val user = response.body()!!.user
                        if (user.email.equals(email) && user.password.equals(password)) {
                            iLoginView.onLoginSuccess("Login Success")
                        } else {
                            iLoginView.onLoginFail("Your email or password is wrong!")
                        }
                    } else {
                        Log.d("Check1", response.body()?.message.toString())
                    }
                }
            })
    }
}
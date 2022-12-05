package com.example.loginmvp.View

interface ILoginView {
    fun onLoginSuccess(message: String)
    fun onLoginFail(message: String)
}
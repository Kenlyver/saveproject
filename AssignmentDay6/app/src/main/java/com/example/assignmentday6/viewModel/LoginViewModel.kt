package com.example.assignmentday6.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentday6.Interface.LoginResultCallBack
import com.example.assignmentday6.Model.LoginUser


class LoginViewModel(private val listener: LoginResultCallBack) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = "Login"

    fun onClick(view: View?) {
        val loginUser = LoginUser(email.value.toString(), password.value.toString())
        if (loginUser.isDataValid) {
            if (loginUser.email.equals("Kenlyver@gmail.com") && loginUser.password.equals("1111111")) {
                listener.onSuccess("Login Success")
            } else listener.onError("Login Fail")
        } else {
            listener.onError("Login Fail")
        }
    }

}
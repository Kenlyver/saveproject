package com.example.assignmentday6t3.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentday6t3.Interface.LoginResultCallBack
import com.example.assignmentday6t3.model.LoginUser
import com.example.assignmentday6t3.model.RegisterUser

class MainViewModel(private val listener: LoginResultCallBack) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val _errorToast = MutableLiveData<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast


    fun onCLick(view: View?) {
        val loginUser = LoginUser(email.value.toString(), password.value.toString())
        if (loginUser.isDataValid) {
            if (email.value.toString().equals("Kenlyver@gmail.com") && password.value.toString()
                    .equals("1111111")
            ) {
                Log.d("TestLogin", "Login Success")
            } else {
                Log.d("TestLogin", "Login Fail")
            }
        } else {
            Log.d("TestLogin", "Login Fail")
        }
    }

    fun submitRegister(view: View?) {
        val registerUser = RegisterUser(
            email.value.toString(),
            password.value.toString(),
            confirmPassword.value.toString()
        )
        if (email.value == null || password.value == null || confirmPassword.value == null) {
            _errorToast.value = true
            listener.onError("Can't null")
        } else if (!registerUser.isDataValid) {
            listener.onError("Wrong format email or password not match")
        } else {
            listener.onSuccess("Register Success")
        }
    }

    fun doneToast() {
        _errorToast.value = false
    }
}
package com.example.jetpackday1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel:ViewModel() {
    val username = MutableLiveData("")
    val password = MutableLiveData("")
    val account = MutableLiveData("")

    fun login(){
        account.value= "${username.value} - ${password.value}"
    }
}
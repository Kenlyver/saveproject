package com.example.assignmentday6t3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentday6t3.Interface.LoginResultCallBack

class RegisterViewModelFactory(private val listener: LoginResultCallBack) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(listener) as T
    }
}
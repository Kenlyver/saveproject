package com.example.jetpackday1.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ParametterViewModelFactory(
    val app:Application,
    val number:Int
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ParametterViewModel::class.java)){
            return ParametterViewModel(app,number) as T
        }
        throw IllegalArgumentException("Unknown VM")
    }
}
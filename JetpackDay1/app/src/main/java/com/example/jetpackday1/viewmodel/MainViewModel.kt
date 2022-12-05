package com.example.jetpackday1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackday1.extentions.SingleLiveEvent

class MainViewModel : ViewModel() {
    private val _number = MutableLiveData(0)
    val number: LiveData<Int>
        // Khoong thay doi dc
        get() = _number

    //    private val _needToast = MutableLiveData(false)
    private val _needToast = SingleLiveEvent<Boolean>()
    val needToast: LiveData<Boolean>
        // Khoong thay doi dc
        get() = _needToast

    fun upNumber() {
        _number.value = number.value?.plus(1)
//        _number.postValue(number.value?.plus(1))
    }

    fun showToast() {
        _needToast.value = true
    }
}
package com.example.tekotest.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tekotest.data.source.AppRepository

class ProductViewModelFactory constructor(
    private val repository: AppRepository,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(this.repository, this.app) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
package com.example.serverorder.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.serverorder.model.Customer
import com.example.serverorder.source.DataRespository
import kotlinx.coroutines.launch

class AddViewModel(app: Application) : AndroidViewModel(app) {
    private val respository = DataRespository.getInstance(app.applicationContext)
    val getCustomer = respository.getAllCustomer()
    val date = MutableLiveData("")
    fun addCustomer(c: Customer) {
        viewModelScope.launch {
            respository.insertCustomer(c)
        }
    }
}
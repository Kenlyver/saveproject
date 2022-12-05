package com.example.serverorder.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverorder.model.Order
import com.example.serverorder.source.DataRespository
import kotlinx.coroutines.launch

class ShowViewModel(app: Application) : AndroidViewModel(app) {
    private val respository = DataRespository.getInstance(app.applicationContext)
    val getOrder = respository.getAllOrder()

    fun insertOrder(o: Order) {
        viewModelScope.launch {
            respository.insertOrder(o)
        }
    }

    fun updateOrder(o: Order) {
        viewModelScope.launch {
            respository.updateOrder(o)
        }
    }

    fun deleteOrder(o: Order) {
        viewModelScope.launch {
            respository.deleteOrder(o)
        }
    }
}
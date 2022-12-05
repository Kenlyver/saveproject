package com.example.tekotest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tekotest.data.database.daos.AppDatabase
import com.example.tekotest.data.models.Color
import com.example.tekotest.data.models.Product
import com.example.tekotest.data.models.ProductUpdate
import com.example.tekotest.data.source.AppRepository
import kotlinx.coroutines.*

class ProductViewModel constructor(private val appRepository: AppRepository, app: Application) :
    AndroidViewModel(app) {
    private val productDao = AppDatabase.getInstant(app.applicationContext).productDao()
    private val productUpdateDao = AppDatabase.getInstant(app.applicationContext).productUpdateDao()
    val errorMessage = MutableLiveData<String>()
    val productList = MutableLiveData<List<Product>>()
    val colorList = MutableLiveData<List<Color>>()


    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val products = productDao.getProduct()
    val getAllUpdate = productUpdateDao.getAllUpdate()
    fun insertProduct(p: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productDao.insert(p)
        }
    }

    fun insertUpdate(p: ProductUpdate) {
        viewModelScope.launch(Dispatchers.IO) {
            productUpdateDao.insert(p)
        }
    }

    fun deleteAllUpdate() {
        viewModelScope.launch(Dispatchers.IO) { }
        productUpdateDao.deleteAll()
    }

    fun updateProduct(p: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productDao.update(p)
        }
    }

    fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = appRepository.getAllProducts()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    productList.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    fun getColors() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = appRepository.getColors()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    colorList.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
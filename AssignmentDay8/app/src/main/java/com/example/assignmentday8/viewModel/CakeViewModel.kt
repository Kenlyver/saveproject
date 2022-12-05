package com.example.assignmentday8.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.assignmentday8.model.dbCake.Cake
import com.example.assignmentday8.model.dbCake.CakeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CakeViewModel(app: Application) : AndroidViewModel(app) {

    private val cakeDao = CakeDatabase.getInstance(app.applicationContext).cakeDao()

    val cake = cakeDao.getCakeLiveData()

    private val _numberCake = MutableLiveData(0)
    val numberCake: LiveData<Int>
        get() = _numberCake

    fun insertCake(cake: Cake) {
        viewModelScope.launch(Dispatchers.IO) {
            cakeDao.insertCake(cake)
        }
    }

    fun updateCake(cake: Cake) {
        viewModelScope.launch(Dispatchers.IO) {
            cakeDao.updateCake(cake)
        }
    }

    fun upCake() {
        _numberCake.value = numberCake.value?.plus(1)
    }

    fun downCake() {
        _numberCake.value = numberCake.value?.minus(1)
    }
    fun resetCake(){
        _numberCake.value = 0
    }
}
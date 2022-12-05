package com.example.teamanagement.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamanagement.model.Tea
import com.example.teamanagement.model.TeaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeaViewModel(app: Application) : AndroidViewModel(app) {
    private val teaDao = TeaDatabase.getInstance(app.applicationContext).teaDao()
    val tea = teaDao.getAllTeaLiveData()
    fun insertTea(t: Tea) {
        viewModelScope.launch(Dispatchers.IO) {
            teaDao.insertTea(t)
        }
    }

    fun updateTea(t: Tea) {
        viewModelScope.launch(Dispatchers.IO) {
            teaDao.updateTea(t)
        }
    }

    fun deleteTeaId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            teaDao.deleteTeaId(id)
        }
    }

    fun getTea(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            teaDao.getTeaLiveData(1)
            Log.d("test", teaDao.getTeaLiveData(1).toString())
        }
    }
}
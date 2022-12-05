package com.example.sensorapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientcar.model.DataTPMS
import com.example.clientcar.model.TPMSDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TPMSViewModel(app: Application) : AndroidViewModel(app) {
    private val tpmsDao = TPMSDatabase.getInstance(app.applicationContext).tpmsDao()
    val tpmsData = tpmsDao.getData()

    fun insertData(data: DataTPMS){
        viewModelScope.launch(Dispatchers.IO) {
            tpmsDao.insertData(data)
        }
    }

    fun updateData(data:DataTPMS){
        viewModelScope.launch(Dispatchers.IO) {
            tpmsDao.updateData(data)
        }
    }
}
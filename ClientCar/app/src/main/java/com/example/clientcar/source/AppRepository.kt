package com.example.clientcar.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.clientcar.model.DataTPMS

class AppRepository(val localData: LocalData) : Database {
    companion object {
        private var instance: AppRepository? = null

        @Synchronized
        fun getInstance(c: Context): AppRepository {
            if (instance == null) {
                val ld = LocalData.getInstance(c)
                instance = AppRepository(ld)
            }
            return instance!!
        }
    }

    override suspend fun insertData(p: DataTPMS) {
        localData.insertData(p)
    }

    override suspend fun updateData(p: DataTPMS) {
        localData.updateData(p)
    }

    override suspend fun deleteData(p: DataTPMS) {
        localData.deleteData(p)
    }

    override fun getData(): LiveData<List<DataTPMS>> {
        return localData.getData()
    }
}
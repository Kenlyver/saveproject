package com.example.clientcar.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.clientcar.model.DataTPMS
import com.example.clientcar.model.TPMSDatabase

class LocalData(tpmsDatabase: TPMSDatabase):Database {
    private val tpmsDao = tpmsDatabase.tpmsDao()
    companion object {
        private var instance: LocalData? = null

        @Synchronized
        fun getInstance(c: Context): LocalData {
            if (instance == null) {
                val db = TPMSDatabase.getInstance(c)
                instance = LocalData(db)
            }
            return instance!!
        }
    }

    override suspend fun insertData(p: DataTPMS) {
        tpmsDao.insertData(p)
    }

    override suspend fun updateData(p: DataTPMS) {
        tpmsDao.updateData(p)
    }

    override suspend fun deleteData(p: DataTPMS) {
        tpmsDao.deleteData(p)
    }

    override fun getData(): LiveData<List<DataTPMS>> {
        return tpmsDao.getData()
    }
}
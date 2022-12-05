package com.example.serverorder.source

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.serverorder.model.ConnectTable
import com.example.serverorder.model.Customer
import com.example.serverorder.model.Order

class DataRespository(c: Context) : Database {

    private val db = LocalData.getInsance(c)

    companion object {
        private var instance: DataRespository? = null

        fun getInstance(c: Context): DataRespository {
            if (instance == null) {
                instance = DataRespository(c)
            }
            return instance!!
        }
    }

    override suspend fun insertCustomer(c: Customer): Long {
        return db.insertCustomer(c)
    }

    override suspend fun updateCustomer(c: Customer) {
        db.updateCustomer(c)
    }

    override suspend fun deleteCustomer(c: Customer) {
        db.deleteCustomer(c)
    }

    override fun getAllCustomer(): LiveData<List<Customer>> = db.getAllCustomer()

    override suspend fun insertOrder(o: Order) {
        db.insertOrder(o)
    }

    override suspend fun updateOrder(o: Order) {
        db.updateOrder(o)
    }

    override suspend fun deleteOrder(o: Order) {
        db.deleteOrder(o)
    }

    override fun getAllOrder(): LiveData<List<ConnectTable>> = db.getAllOrder()
    override fun getInfor(): Cursor {
        return db.getInfor()
    }
}
package com.example.serverorder.source

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.serverorder.model.AppDatabase
import com.example.serverorder.model.ConnectTable
import com.example.serverorder.model.Customer
import com.example.serverorder.model.Order

class LocalData(db: AppDatabase) : Database {
    private val orderDao = db.orderDao()
    private val customerDao = db.customerDao()

    companion object {
        private var instance: LocalData? = null

        fun getInsance(c: Context): LocalData {
            if (instance == null) {
                val database = AppDatabase.getInstance(c)
                instance = LocalData(database)
            }
            return instance!!
        }
    }

    override suspend fun insertCustomer(c: Customer): Long {
        return customerDao.insertCustomer(c)
    }

    override suspend fun updateCustomer(c: Customer) {
        customerDao.updateCustomer(c)
    }

    override suspend fun deleteCustomer(c: Customer) {
        customerDao.deleteCustomer(c)
    }

    override fun getAllCustomer(): LiveData<List<Customer>> = customerDao.getAllCustomer()

    override suspend fun insertOrder(o: Order) {
        orderDao.insertOrder(o)
    }

    override suspend fun updateOrder(o: Order) {
        orderDao.updateOrder(o)
    }

    override suspend fun deleteOrder(o: Order) {
        orderDao.deleteOrder(o)
    }

    override fun getAllOrder(): LiveData<List<ConnectTable>> = orderDao.getAllOrder()
    override fun getInfor(): Cursor {
        return orderDao.getInfor()
    }
}
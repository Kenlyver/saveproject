package com.example.serverorder.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(c: Customer): Long

    @Update
    suspend fun updateCustomer(c: Customer)

    @Delete
    suspend fun deleteCustomer(c: Customer)

    @Query("SELECT * FROM tb_customer")
    fun getAllCustomer(): LiveData<List<Customer>>
}
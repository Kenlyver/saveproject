package com.example.serverorder.model

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(o: Order)

    @Update
    suspend fun updateOrder(o: Order)

    @Delete
    suspend fun deleteOrder(o: Order)

    @Query("SELECT * FROM tb_order")
    fun getAllOrder(): LiveData<List<ConnectTable>>

    @Query("Select c.col_name,o.col_time,sum(o.col_value) as `total value`, max(o.col_value) as `max`  from tb_order as o,tb_customer as c where  o.col_idCustomer = c.col_id group by col_time")
    fun getInfor(): Cursor
}
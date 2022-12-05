package com.example.clientcar.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TPMSDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(p:DataTPMS)

    @Update
    suspend fun updateData(p:DataTPMS)

    @Delete
    suspend fun deleteData(p:DataTPMS)

    @Query("SELECT * FROM DataTPMS WHERE col_id = (SELECT MAX(col_id) FROM DataTPMS)")
    fun getData():LiveData<List<DataTPMS>>
}
package com.example.teamanagement.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTea(t: Tea)

    @Update
    suspend fun updateTea(t: Tea)

    @Delete
    suspend fun deleteTea(t: Tea)

    @Query("SELECT * FROM tb_tea")
    fun getAllTeaLiveData(): LiveData<List<Tea>>

    @Query("SELECT * FROM tb_tea WHERE col_id = :i")
    fun getTeaLiveData(i: Int): LiveData<Tea>

    @Query("DELETE FROM tb_tea WHERE col_id = :id")
    fun deleteTeaId(id: Int)
}
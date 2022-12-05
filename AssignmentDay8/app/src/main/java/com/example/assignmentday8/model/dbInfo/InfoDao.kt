package com.example.assignmentday8.model.dbInfo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(info:Info)

    @Update
    suspend fun updateInfo(info: Info)

    @Query("SELECT * FROM tb_info")
    fun getInfoLiveData():LiveData<List<Info>>

    @Query("DELETE FROM tb_info")
    fun deleteAll()
}
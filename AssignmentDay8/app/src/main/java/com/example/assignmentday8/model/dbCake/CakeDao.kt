package com.example.assignmentday8.model.dbCake

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CakeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCake(cake: Cake)

    @Update
    suspend fun updateCake(cake: Cake)

    @Query("SELECT * FROM tb_cake")
    fun getCakeLiveData():LiveData<Cake>

    @Query("UPDATE tb_cake SET col_amount = :amount")
    fun updateAmountCake(amount:Int)
}
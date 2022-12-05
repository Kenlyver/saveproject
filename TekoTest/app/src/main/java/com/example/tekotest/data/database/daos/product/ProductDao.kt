package com.example.tekotest.data.database.daos.product

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tekotest.data.models.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg t: Product): LongArray

    @Update
    suspend fun update(t: Product)

    @Query("SELECT * FROM `product`")
    fun getProduct(): LiveData<List<Product>>

}
package com.example.tekotest.data.database.daos.product

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tekotest.data.models.Product
import com.example.tekotest.data.models.ProductUpdate

@Dao
interface ProductUpdateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: ProductUpdate): Long

    @Query("DELETE FROM `update_save`")
    fun deleteAll()

    @Query("SELECT * FROM `update_save`")
    fun getAllUpdate(): LiveData<List<ProductUpdate>>
}
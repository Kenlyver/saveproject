package com.example.assignmentday8.model.dbWallet

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.assignmentday8.model.dbCake.Cake

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(wallet: Wallet)

    @Query("SELECT * FROM tb_wallet")
    fun getWalletLiveData():LiveData<Wallet>

    @Query("UPDATE tb_wallet SET col_vnd = :wallet")
    fun updateVND(wallet: String)

    @Query("UPDATE tb_wallet SET col_usd = :wallet")
    fun updateUSD(wallet: String)
}
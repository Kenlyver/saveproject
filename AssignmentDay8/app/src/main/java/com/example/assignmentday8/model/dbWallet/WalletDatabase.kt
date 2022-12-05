package com.example.assignmentday8.model.dbWallet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignmentday8.model.dbCake.Cake
import com.example.assignmentday8.model.dbCake.CakeDao
import com.example.assignmentday8.model.dbCake.CakeDatabase

@Database(entities = [Wallet::class], version = 1, exportSchema = false)
abstract class WalletDatabase:RoomDatabase() {
    abstract fun walletDao(): WalletDao

    companion object {
        private var instance: WalletDatabase? = null

        @Synchronized
        fun getInstance(c: Context): WalletDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    WalletDatabase::class.java,
                    "wallet.db"
                ).build()
            }
            return instance!!
        }
    }
}
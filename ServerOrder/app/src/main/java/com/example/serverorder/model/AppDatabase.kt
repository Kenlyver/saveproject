package com.example.serverorder.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Customer::class, Order::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun customerDao(): CustomerDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(c: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    AppDatabase::class.java,
                    "database.db"
                ).build()
            }
            return instance!!
        }
    }
}
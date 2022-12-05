package com.example.assignmentday8.model.dbCake

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cake::class], version = 1, exportSchema = false)
abstract class CakeDatabase : RoomDatabase() {
    abstract fun cakeDao(): CakeDao

    companion object {
        private var instance: CakeDatabase? = null

        @Synchronized
        fun getInstance(c: Context): CakeDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    CakeDatabase::class.java,
                    "cake.db"
                ).build()
            }
            return instance!!
        }
    }
}
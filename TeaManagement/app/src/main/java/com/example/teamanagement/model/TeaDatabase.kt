package com.example.teamanagement.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tea::class], version = 1, exportSchema = false)
abstract class TeaDatabase : RoomDatabase() {
    abstract fun teaDao(): TeaDao

    companion object {
        private var instance: TeaDatabase? = null

        @Synchronized
        fun getInstance(c: Context): TeaDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    TeaDatabase::class.java,
                    "tea.db"
                ).build()
            }
            return instance!!
        }
    }
}
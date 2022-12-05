package com.example.serverstudentapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        private var instance: StudentDatabase? = null

        @Synchronized
        fun getInstance(c: Context): StudentDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    StudentDatabase::class.java,
                    "student.db"
                ).build()
            }
            return instance!!
        }
    }
}
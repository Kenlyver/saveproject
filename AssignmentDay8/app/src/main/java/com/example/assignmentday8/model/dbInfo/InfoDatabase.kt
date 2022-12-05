package com.example.assignmentday8.model.dbInfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Info::class], version = 1, exportSchema = false)
abstract class InfoDatabase:RoomDatabase() {
    abstract fun infoDao():InfoDao

    companion object{
        private var instance:InfoDatabase? = null

        @Synchronized
        fun getInstance(c: Context):InfoDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    InfoDatabase::class.java,
                    "info.db"
                ).build()
            }
            return instance!!
        }
    }
}
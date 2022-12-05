package com.example.clientcar.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataTPMS::class], version = 1, exportSchema = false)
abstract class TPMSDatabase:RoomDatabase() {
    abstract fun tpmsDao():TPMSDao
    companion object{
        private var instance:TPMSDatabase?=null
        @Synchronized
        fun getInstance(c: Context):TPMSDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    TPMSDatabase::class.java,
                    "pressure.db"
                ).build()
            }
            return instance!!
        }
    }
}
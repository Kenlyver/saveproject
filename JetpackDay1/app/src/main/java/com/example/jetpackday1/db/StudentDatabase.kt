package com.example.jetpackday1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase:RoomDatabase() {
    abstract fun studentDao():StudentDao

    companion object{
        private var instance:StudentDatabase? = null

        //singleton pattern
        @Synchronized // tai mot thoi diem chi co 1 thang
        fun getInstance(ctx: Context):StudentDatabase{
            if(instance == null){
                //data/data/package/database/tb_student.db
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    StudentDatabase::class.java,
                    "student.db"
                ).build()
            }
            return instance!!
        }
    }
}
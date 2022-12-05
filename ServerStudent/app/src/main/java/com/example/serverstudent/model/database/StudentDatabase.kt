package com.example.serverstudent.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.serverstudent.entity.Student
import com.example.serverstudent.model.dao.StudentDao

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}
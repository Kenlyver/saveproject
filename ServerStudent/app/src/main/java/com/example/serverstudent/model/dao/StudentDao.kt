package com.example.serverstudent.model.dao

import androidx.room.*
import com.example.serverstudent.entity.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(s: Student):Long

    @Update
    suspend fun updateStudent(s: Student):Int

    @Delete
    suspend fun deleteStudent(s: Student):Int

    @Query("SELECT * FROM Student")
    fun getAllStudent():List<Student>


}
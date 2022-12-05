package com.example.serverstudentapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(s: Student)

    @Update
    suspend fun updateStudent(s: Student)

    @Delete
    suspend fun deleteStudent(s: Student)

    @Query("SELECT * FROM Student")
    fun getAllStudent(): LiveData<List<Student>>
}
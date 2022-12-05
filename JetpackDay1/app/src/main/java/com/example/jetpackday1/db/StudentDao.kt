package com.example.jetpackday1.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(s:Student)

    @Update
    suspend fun updateStudent(s:Student)

    @Delete
    suspend fun deleteStudent(s:Student)

    @Query("DELETE FROM tb_student WHERE col_id = id")
    suspend fun deleteStudentById(id:Int)

    @Query("SELECT * FROM tb_student")
    suspend fun getAllStudents():List<Student>

    @Query("SELECT * FROM tb_student")
    fun getAllStudentsLiveData():LiveData<List<Student>>

    @Query("SELECT * FROM tb_student")
    fun getStudentWithPage():PagingSource<Int,Student>
}
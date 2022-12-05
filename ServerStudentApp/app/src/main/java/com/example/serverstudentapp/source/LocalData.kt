package com.example.serverstudentapp.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.serverstudentapp.model.Student
import com.example.serverstudentapp.model.StudentDatabase

class LocalData(studentDatabase: StudentDatabase) : Database {

    private val studentDao = studentDatabase.studentDao()

    companion object {
        private var instance: LocalData? = null

        @Synchronized
        fun getInstance(c: Context): LocalData {
            if (instance == null) {
                val db = StudentDatabase.getInstance(c)
                instance = LocalData(db)
            }
            return instance!!
        }
    }

    override suspend fun insertStudent(s: Student) {
        studentDao.insertStudent(s)
    }

    override suspend fun updateStudent(s: Student) {
        studentDao.updateStudent(s)
    }

    override suspend fun deleteStudent(s: Student) {
        studentDao.deleteStudent(s)
    }

    override fun getAllStudent(): LiveData<List<Student>> {
        return studentDao.getAllStudent()
    }
}
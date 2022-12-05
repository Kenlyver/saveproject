package com.example.serverstudentapp.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.serverstudentapp.model.Student

class StudentRepository(val localData: LocalData) : Database {
    companion object {
        private var instance: StudentRepository? = null

        @Synchronized
        fun getInstance(c: Context): StudentRepository {
            if (instance == null) {
                val ld = LocalData.getInstance(c)
                instance = StudentRepository(ld)
            }
            return instance!!
        }
    }

    override suspend fun insertStudent(s: Student) {
        localData.insertStudent(s)
    }

    override suspend fun updateStudent(s: Student) {
        localData.updateStudent(s)
    }

    override suspend fun deleteStudent(s: Student) {
        localData.deleteStudent(s)
    }

    override fun getAllStudent(): LiveData<List<Student>> {
        return localData.getAllStudent()
    }
}
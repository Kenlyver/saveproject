package com.example.jetpackday1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.jetpackday1.db.Student
import com.example.jetpackday1.db.StudentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(app: Application) : AndroidViewModel(app) {
    private val studentDao = StudentDatabase.getInstance(app.applicationContext).studentDao()
    val students = studentDao.getAllStudentsLiveData()

    val studentPage = Pager(PagingConfig(pageSize = 20)){
        studentDao.getStudentWithPage()
    }.flow.cachedIn(viewModelScope)

    fun insertStudent(s: Student){
        viewModelScope.launch( Dispatchers.IO){
            studentDao.insertStudent(s)
        }
    }
    fun updateStudent(s:Student){
        viewModelScope.launch( Dispatchers.IO){
            studentDao.updateStudent(s)
        }
    }
    fun deleteStudentById(id:Int){
        viewModelScope.launch( Dispatchers.IO){
            studentDao.deleteStudentById(id)
        }
    }
}
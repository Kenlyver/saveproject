package com.example.serverstudent.controller

import android.content.Context
import android.util.Log
import com.example.serverstudent.base.CallbackProvider
import com.example.serverstudent.entity.FailureResponse
import com.example.serverstudent.entity.Student
import com.example.serverstudent.entity.StudentResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentServiceController(context: Context) : StudentServiceConnector.Callback,
    CallbackProvider<StudentServiceConnector.Callback> {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val serviceProvider = StudentServiceConnector.Builder(context)
        .setCallback(this)
        .build()

    override val callbacks: ArrayList<StudentServiceConnector.Callback> = ArrayList()

    override fun addCallback(callback: StudentServiceConnector.Callback) {
        super.addCallback(callback)
        if (callbacks.size > 0) {
            connect()
            Log.d("Server in Controller","Connected")
        }
    }

    override fun removeCallback(callback: StudentServiceConnector.Callback) {
        super.removeCallback(callback)
        if (callbacks.size == 0) {
            disconnect()
        }
    }

    private fun connect() {
        serviceProvider.connectService()
    }

    private fun disconnect() {
        serviceProvider.disconnectService()
    }

    fun getAllStudent() {
        serviceProvider.getAllStudentRequest()
    }

    fun insertStudent(s: Student) {
        serviceProvider.insertStudentRequest(s)
    }

    fun updateStudent(s: Student) {
        serviceProvider.updateStudentRequest(s)
    }

    fun deleteStudent(s: Student) {
        serviceProvider.deleteStudentRequest(s)
    }

    override fun onStudentServiceConnected() {
        coroutineScope.launch {
            callbacks.forEach {
                it.onStudentServiceConnected()
            }
        }
    }

    override fun onGetAllStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                Log.d("testOnGetAll",response.toString())
                it.onGetAllStudentResponse(response)
            }
        }
    }

    override fun onInsertStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onInsertStudentResponse(response)
            }
        }
    }

    override fun onUpdateStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onUpdateStudentResponse(response)
            }
        }
    }

    override fun onDeleteStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onDeleteStudentResponse(response)
            }
        }
    }

    override fun onFailureResponse(response: FailureResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onFailureResponse(response)
            }
        }
    }


}
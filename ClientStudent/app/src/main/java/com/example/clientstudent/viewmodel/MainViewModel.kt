package com.example.clientstudent.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.clientstudent.ClientApplication
import com.example.clientstudent.R
import com.example.serverstudent.controller.StudentServiceConnector
import com.example.serverstudent.controller.StudentServiceController
import com.example.serverstudent.entity.FailureResponse
import com.example.serverstudent.entity.ResponseCode
import com.example.serverstudent.entity.Student
import com.example.serverstudent.entity.StudentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val studentServiceController: StudentServiceController
) : ViewModel(), LifecycleObserver, StudentServiceConnector.Callback {
    var students: MutableLiveData<List<Student>> = MutableLiveData()
        private set

    override fun onStudentServiceConnected() {
        ClientApplication.toast(context, context.getString(R.string.connected_success))
        fetchAllStudent()
    }

    override fun onGetAllStudentResponse(response: StudentResponse) {
        Log.d("testonG",response.students.toString())
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.toast(context, context.getString(R.string.fetch_success))
            Log.d("test",response.students.toString())
            students.value = response.students
        }
    }

    override fun onInsertStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.toast(
                context,
                context.getString(R.string.insert_success)
            )
            students.value = response.students
        }
    }

    override fun onUpdateStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.toast(
                context,
                context.getString(R.string.update_success)
            )
            students.value = response.students
        }
    }

    override fun onDeleteStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.toast(context, context.getString(R.string.delete_success))
        }
        students.value = response.students
    }

    override fun onFailureResponse(response: FailureResponse) {
        when (response.responseCode) {
            ResponseCode.ERROR_SELECT_STUDENTS -> ClientApplication.toast(
                context,
                context.getString(R.string.error_sellect)
            )
            ResponseCode.ERROR_INSERT -> ClientApplication.toast(
                context,
                context.getString(R.string.error_insert)
            )
            ResponseCode.ERROR_UPDATE -> ClientApplication.toast(
                context,
                context.getString(R.string.error_update)
            )
            ResponseCode.ERROR_DELETE -> ClientApplication.toast(
                context,
                context.getString(R.string.error_delete),
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        studentServiceController.addCallback(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        studentServiceController.removeCallback(this)
    }

    fun insertStudent(s: Student) {
        studentServiceController.insertStudent(s)
    }

    fun updateStudent(s: Student) {
        studentServiceController.updateStudent(s)
    }

    fun deleteStudent(s: Student) {
        studentServiceController.deleteStudent(s)
    }

    private fun fetchAllStudent() {
        studentServiceController.getAllStudent()
    }
}
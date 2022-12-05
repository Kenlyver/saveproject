package com.example.serverstudent.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.util.Log
import com.example.serverstudent.R
import com.example.serverstudent.entity.*
import com.example.serverstudent.model.dao.StudentDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ServerService : Service() {
    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var studentServiceCallbacks: RemoteCallbackList<IStudentServiceCallback>

    @Inject
    lateinit var studentDao: StudentDao
    override fun onBind(intent: Intent?): IBinder? {
        if (intent == null) return null
        if (intent.action == getString(R.string.action_student_service)) {
            return StudentBinder()
        }
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TestDestroy","Error")
        studentServiceCallbacks.kill()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d("ServerTest", "onLowMemory")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    inner class StudentBinder : IStudentService.Stub() {

        override fun registerCallback(callback: IStudentServiceCallback?) {
            if (callback != null) {
                Log.d("register",callback.toString())
                studentServiceCallbacks.register(callback, this@StudentBinder)
            }
        }

        override fun unregisterCallback(callback: IStudentServiceCallback?) {
            if (callback != null) studentServiceCallbacks.unregister(callback)
        }

        override fun getAllStudentRequest() {
            Log.e("ServerTest", "getAllStudentRequest: ")
            scope.launch {
                val listStudents: List<Student> = getAllStudents()
                if (listStudents == null) {
                    postFailureResponse(
                        RequestCode.GET_STUDENTS,
                        ResponseCode.ERROR_SELECT_STUDENTS
                    )
                    return@launch
                } else {
                    Log.e("ServerTest", "getAllStudentRequest: 4")
                    remoteBroadcast { index ->
                        Log.e("ServerTest", "getAllStudentRequest: ${listStudents} ")
                        studentServiceCallbacks.getBroadcastItem(index).onGetAllStudentResponse(
                            StudentResponse(
                                ResponseCode.OK,
                                null,
                                listStudents
                            )
                        )
                    }
                }
            }
        }

        override fun insertStudentRequest(student: Student) {
            Log.e("ServerTest", "insertStudentRequest: ${student.toString()}")
            scope.launch {
                val resultId = studentDao.insertStudent(studentCastDetail(student))
                if (resultId.toString().toInt() <= -1) {
                    postFailureResponse(RequestCode.INSERT_REQ, ResponseCode.ERROR_INSERT)
                    return@launch
                } else {
                    val listStudents: List<Student> = getAllStudents()
                    remoteBroadcast { index ->
                        studentServiceCallbacks.getBroadcastItem(index).onInsertStudentResponse(
                            StudentResponse(
                                ResponseCode.OK,
                                resultId.toString().toInt(),
                                listStudents
                            )
                        )
                    }
                }
            }
        }

        override fun updateStudentRequest(student: Student) {
            Log.e("ServerTest", "updateStudentRequest: $student")
            scope.launch {
                val resultId = studentDao.updateStudent(studentCastDetail(student))
                if (resultId.toString().toInt() < 0) {
                    postFailureResponse(RequestCode.UPDATE_REQ, ResponseCode.ERROR_UPDATE)
                    return@launch
                } else {
                    val listStudents: List<Student> = getAllStudents()
                    remoteBroadcast { index ->
                        studentServiceCallbacks.getBroadcastItem(index).onUpdateStudentResponse(
                            StudentResponse(
                                ResponseCode.OK,
                                resultId.toString().toInt(),
                                listStudents
                            )
                        )
                    }
                }
            }
        }

        override fun deleteStudentRequest(student: Student) {
            Log.e("ServerTest", "deleteStudentRequest: $student")
            scope.launch {
                val resultId = studentDao.deleteStudent(studentCastDetail(student))
                if (resultId.toString().toInt() <= 0) {
                    postFailureResponse(RequestCode.DELETE_REQ, ResponseCode.ERROR_DELETE)
                    return@launch
                } else {
                    val listStudents: List<Student> = getAllStudents()
                    remoteBroadcast { index ->
                        studentServiceCallbacks.getBroadcastItem(index).onDeleteStudentResponse(
                            StudentResponse(
                                ResponseCode.OK,
                                resultId.toString().toInt(),
                                listStudents
                            )
                        )
                    }
                }
            }
        }

        private fun postFailureResponse(
            @RequestCode requestCode: Int,
            @ResponseCode responseCode: Int
        ) {
            remoteBroadcast { index ->
                studentServiceCallbacks.getBroadcastItem(index).onFailureResponse(
                    FailureResponse(requestCode, responseCode)
                )
            }
        }

        private fun remoteBroadcast(block: (Int) -> Unit) {
            val count = studentServiceCallbacks.beginBroadcast()
            Log.d("testCount",count.toString())
            Log.e("ServerTest", "remoteBroadcast: 3")
            for (index in 0 until count) {
                Log.e("ServerTest", "remoteBroadcast:0")
                if (studentServiceCallbacks.getBroadcastCookie(index) == this@StudentBinder) {
                    Log.e("ServerTest", "remoteBroadcast: 2")
                    block.invoke(index)
                    break
                } else {
                    Log.e("ServerTest", "remoteBroadcast:1 ")
                }
            }
            studentServiceCallbacks.finishBroadcast()
        }

    }

    private fun studentCastDetail(student: Student): Student {
        val (id, name, age, mathP, physicP, englishP) = student
        return Student(id, name,age, mathP, physicP, englishP)
    }

    private fun getAllStudents(): List<Student> {
        return studentDao.getAllStudent().map {
            val (id, name,age, mathP, physicP, englishP) = it
            Student(id, name,age, mathP, physicP, englishP)
        }
    }
}
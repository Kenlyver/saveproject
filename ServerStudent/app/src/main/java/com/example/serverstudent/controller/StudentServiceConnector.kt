package com.example.serverstudent.controller

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.serverstudent.R
import com.example.serverstudent.entity.*

class StudentServiceConnector private constructor(private val context: Context){
    var serviceConnected = false
    private var studentService: IStudentService? = null
    var callback:Callback? = null

    private val serviceConnection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?){
            serviceConnected = true
            studentService = IStudentService.Stub.asInterface(service)
            try{
                studentService?.registerCallback(studentServiceCallback)
            }catch (e:RemoteException){
                e.printStackTrace()
            }
            callback?.onStudentServiceConnected()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceConnected = false
            studentService = null
        }
    }
    private val studentServiceCallback = object: IStudentServiceCallback.Stub(){
        override fun onGetAllStudentResponse(response: StudentResponse?) {
            if (response != null) {
                callback?.onGetAllStudentResponse(response)
            }
        }

        override fun onInsertStudentResponse(response: StudentResponse?) {
            if (response != null) {
                callback?.onInsertStudentResponse(response)
            }
        }

        override fun onUpdateStudentResponse(response: StudentResponse?) {
            if (response != null) {
                callback?.onUpdateStudentResponse(response)
            }
        }

        override fun onDeleteStudentResponse(response: StudentResponse?) {
            if (response != null) {
                callback?.onDeleteStudentResponse(response)
            }
        }

        override fun onFailureResponse(failureResponse: FailureResponse?) {
            if (failureResponse != null) {
                callback?.onFailureResponse(failureResponse)
            }
        }

    }

    fun connectService(){
        if(serviceConnected){
            Log.d("Server","Service is connected")
            return
        }
        val intent = Intent()
        intent.apply {
            component = ComponentName.unflattenFromString(context.getString(R.string.component_student_service))
            action = context.getString(R.string.action_student_service)
        }
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE)
    }

    fun getAllStudentRequest(){
        if(!serviceConnected){
            return
        }
        try {
            studentService?.getAllStudentRequest()
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }

    fun insertStudentRequest(s: Student){
        if(!serviceConnected){
            return
        }
        try {
            studentService?.insertStudentRequest(s)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }

    fun updateStudentRequest(s:Student){
        if (!serviceConnected){
            return
        }
        try {
            studentService?.updateStudentRequest(s)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }
    fun deleteStudentRequest(s:Student){
        if(!serviceConnected){
            return
        }
        try {
            studentService?.deleteStudentRequest(s)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }
    fun disconnectService(){
        if(!serviceConnected){
            return
        }
        try{
            studentService?.unregisterCallback(studentServiceCallback)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
        context.unbindService(serviceConnection)
        serviceConnected = false
    }
    class Builder(context: Context){
        private val connectorClient = StudentServiceConnector(context)

        fun setCallback(cb:Callback):Builder{
            connectorClient.callback = cb
            return this@Builder
        }
        fun build():StudentServiceConnector = connectorClient
    }

    interface Callback {

        fun onStudentServiceConnected()

        fun onGetAllStudentResponse(response: StudentResponse)

        fun onInsertStudentResponse(response: StudentResponse)

        fun onUpdateStudentResponse(response: StudentResponse)

        fun onDeleteStudentResponse(response: StudentResponse)

        fun onFailureResponse(response: FailureResponse)
    }
}
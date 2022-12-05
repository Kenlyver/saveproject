package com.example.serverstudentapp.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.lifecycle.LifecycleService
import com.example.serverstudentapp.model.Student
import com.example.serverstudentapp.service.Constant.DELETE
import com.example.serverstudentapp.service.Constant.GET_STUDENT
import com.example.serverstudentapp.service.Constant.INSERT
import com.example.serverstudentapp.service.Constant.R_DATA
import com.example.serverstudentapp.service.Constant.S_DATA
import com.example.serverstudentapp.service.Constant.UPDATE
import com.example.serverstudentapp.source.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class ServerService : LifecycleService() {

    private lateinit var messenger: Messenger
    private lateinit var messengerClient: Messenger
    private lateinit var repository: StudentRepository
    private var checkConnect = false
    private val job = CoroutineScope(Dispatchers.IO)
    private val handler = AppHandler(Looper.getMainLooper()) {
        val student = it.data.getSerializable(S_DATA) as Student
        when (it.what) {
            INSERT -> {
                job.launch {
                    repository.insertStudent(student)
                }
            }
            UPDATE -> {
                job.launch {
                    repository.updateStudent(student)
                }
            }
            DELETE -> {
                job.launch {
                    repository.deleteStudent(student)
                }
            }
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messengerClient = Messenger(service)
            checkConnect = true
            repository.getAllStudent().observe(this@ServerService) {
                val msg = Message.obtain(null, GET_STUDENT)
                val bundle = Bundle()
                bundle.putSerializable(R_DATA, it as Serializable)
                msg.data = bundle
                messengerClient.send(msg)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            checkConnect = false
        }

    }

    override fun onBind(intent: Intent): IBinder? {
        return super.onBind(intent)
        messenger = Messenger(handler)
        return messenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        repository = StudentRepository.getInstance(this.applicationContext)
        Intent(this, ClientService::class.java).apply {
            bindService(this, connection, BIND_AUTO_CREATE)
        }
    }
}
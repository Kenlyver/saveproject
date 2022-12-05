package com.example.clientcar.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.lifecycle.LifecycleService
import com.example.clientcar.model.DataTPMS
import com.example.clientcar.service.Constant.DELETE
import com.example.clientcar.service.Constant.GET_DATA
import com.example.clientcar.service.Constant.INSERT
import com.example.clientcar.service.Constant.R_DATA
import com.example.clientcar.service.Constant.S_DATA
import com.example.clientcar.service.Constant.UPDATE
import com.example.clientcar.source.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class ServerService: LifecycleService() {
    private lateinit var messenger: Messenger
    private lateinit var messengerClient: Messenger
    private lateinit var repository: AppRepository
    private var checkConnect = false
    private val job = CoroutineScope(Dispatchers.IO)

    private val handler = AppHandler(Looper.getMainLooper()){
        val data = it.data.getSerializable(S_DATA) as DataTPMS
        when(it.what){
            INSERT ->{
                job.launch {
                    repository.insertData(data)
                }
            }
            UPDATE->{
                job.launch {
                    repository.updateData(data)
                }
            }
            DELETE->{
                job.launch {
                    repository.deleteData(data)
                }
            }
        }
    }
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messengerClient = Messenger(service)
            checkConnect = true
            repository.getData().observe(this@ServerService) {
                val msg = Message.obtain(null, GET_DATA)
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
        repository = AppRepository.getInstance(this.applicationContext)
        Intent(this, ClientService::class.java).apply {
            bindService(this, connection, BIND_AUTO_CREATE)
        }
    }
}
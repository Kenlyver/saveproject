package com.example.serverstudentapp.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import com.example.serverstudentapp.service.Constant.CONNECT
import com.example.serverstudentapp.service.Constant.DELETE
import com.example.serverstudentapp.service.Constant.GET_STUDENT
import com.example.serverstudentapp.service.Constant.INSERT
import com.example.serverstudentapp.service.Constant.UPDATE

class ClientService : Service() {

    private lateinit var messengerActivity: Messenger
    private lateinit var messengerServer: Messenger
    private lateinit var messenger: Messenger
    private var checkConnect = false
    override fun onBind(intent: Intent?): IBinder? {
        return messenger.binder
    }

    private val handler = AppHandler(Looper.getMainLooper()) {
        when (it.what) {
            CONNECT -> {
                messengerActivity = it.replyTo
            }
            GET_STUDENT -> {
                val message = Message.obtain(null, it.what)
                message.data = it.data
                messengerActivity.send(message)
            }
            INSERT, UPDATE, DELETE -> {
                val message = Message.obtain(null, it.what)
                message.data = it.data
                messengerServer.send(message)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        messenger = Messenger(handler)
        Intent(this, ServerService::class.java).apply {
            bindService(this, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messengerServer = Messenger(service)
            checkConnect = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            checkConnect = false
        }

    }
}
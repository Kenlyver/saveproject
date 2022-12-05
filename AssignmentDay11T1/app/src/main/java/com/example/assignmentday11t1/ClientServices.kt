package com.example.assignmentday11t1

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.core.os.bundleOf

class ClientServices : Service() {
    companion object {
        private const val TAG = "Client"
        private const val CHANNEL_ID = "Client"
        private const val ACTION_STOP = "Stop Client"
        private const val ID_SERVICE_FOREGROUND = 99
    }

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                Constant.START_COMMUNICATE -> {
                    sendData(msg, this)
                }
                Constant.FROM_SERVER -> {
                    val value = msg.data.getString(Constant.VALUE)
                    Log.d(TAG, "Message form server : $value")
                    sendData(msg, this)
                }
                Constant.STOP_CLIENT -> {
                    stopClientMessener()
                }
            }
        }
    }

    private val messenger = Messenger(mHandler)

    override fun onBind(intent: Intent): IBinder = messenger.binder

    override fun onCreate() {
        startForeground(ID_SERVICE_FOREGROUND, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopClientMessener()
            }
            else -> throw IllegalArgumentException("No action")
        }
        return START_STICKY
    }

    fun sendData(msg: Message, handler: Handler) {
        val mServer = msg.replyTo
        val key = (1..10).random()
        val message = Message.obtain().apply {
            data = bundleOf(Constant.KEY to key)
            what = Constant.TO_SERVER
            replyTo = messenger
        }
        handler.postDelayed({ mServer.send(message) }, 5000)
        Log.d(TAG, "Send to server: $key")
    }

    private fun stopClientMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(): Notification {
        val name = "IPC"
        val description = "IPC Messager"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
            }
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

        val pendingIntent = Intent(this, ClientServices::class.java).apply {
            action = ACTION_STOP
        }.let {
            PendingIntent.getService(this, 0, it, 0)
        }

        return Notification.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setContentText("Client is running")
            .setContentTitle("Client")
            .setOngoing(true)
            .build()
    }
}
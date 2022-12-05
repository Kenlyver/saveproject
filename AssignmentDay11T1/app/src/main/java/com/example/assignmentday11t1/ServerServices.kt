package com.example.assignmentday11t1

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.core.os.bundleOf

class ServerServices : Service() {
    companion object {
        private const val TAG = "Server"
        private const val CHANNEL_ID = "Server"
        private const val ACTION_STOP = "Stop Server"
        private const val ID_SERVICE_FOREGROUND = 98
    }

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                Constant.FROM_CLIENT -> {
                    val key = msg.data.getInt(Constant.KEY)
                    Log.d(TAG, "Message from client : $key")
                    val returnMessage = key * 5
                    val value = "Sent from server $returnMessage"
                    replyMessage(msg, value)
                }
                Constant.STOP_SERVER -> {
                    stopServerMessener()
                }
            }
        }
    }

    private val messenger = Messenger(mHandler)

    override fun onBind(intent: Intent): IBinder = messenger.binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopServerMessener()
            }
            else -> throw IllegalArgumentException("No action")
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(ID_SERVICE_FOREGROUND, createNotification())
    }


    private fun replyMessage(message: Message, value: String) {
        Message.obtain().apply {
            data = bundleOf(Constant.VALUE to value)
            what = Constant.TO_CLIENT
            replyTo = messenger
            message.replyTo.send(this)
        }
        Log.d(TAG, "Reply to client: $value")
    }

    private fun stopServerMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
        Log.e(TAG, "Stop server !")
    }

    private fun createNotification(): Notification {
        val name = "IPC"
        val description = "Messager"
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

        val pendingIntent = Intent(this, ServerServices::class.java).apply {
            action = ACTION_STOP
        }.let {
            PendingIntent.getService(this, 0, it, 0)
        }

        return Notification.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setContentText("Server is running ... ")
            .setContentTitle("Server")
            .setOngoing(true)
            .build()
    }

}
package com.example.serverstudentapp.service

import android.os.Handler
import android.os.Looper
import android.os.Message

class AppHandler(looper: Looper, private val callback: (msg: Message) -> Unit) : Handler(looper) {

    override fun handleMessage(msg: Message) {
        callback(msg)
        super.handleMessage(msg)
    }
}
package com.example.clientstudent

import android.app.Application
import android.content.Context
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClientApplication : Application() {
    companion object {
        fun toast(context: Context, messenger: String) {
            Toast.makeText(context, messenger, Toast.LENGTH_LONG).show()
        }
    }
}
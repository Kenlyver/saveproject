package com.example.sensorapp.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.sensorapp.view.MainActivity

class AppReceiver: BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context?, p1: Intent?) {
        try {
            Intent(p0, MainActivity::class.java).apply {
                addCategory(Intent.CATEGORY_DEFAULT)

                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                p0?.startActivity(this)
            }
        } catch (e: Exception) {
            Log.e("finalandroidadvance", " ${e.message}")
        }

    }
}

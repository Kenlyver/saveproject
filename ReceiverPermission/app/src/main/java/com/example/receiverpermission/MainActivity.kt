package com.example.receiverpermission

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val receiver = Receiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filter = IntentFilter("com.kenlyver.EXAMPLE_APP")
        filter.priority = 2
        registerReceiver(receiver, filter, "com.example.CUSTOM_PERMISSION", null)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
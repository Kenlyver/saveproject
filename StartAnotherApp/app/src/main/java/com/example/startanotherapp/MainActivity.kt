package com.example.startanotherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.startanotherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            val launchIntent: Intent? =
                packageManager.getLaunchIntentForPackage("com.vanced.android.youtube")
            startActivity(launchIntent)
        }
        binding.btnStartBroadcast.setOnClickListener {
            val intent = Intent()
            intent.setAction("android.intent.action.Receiver")
            intent.setPackage("com.example.sentlist")
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            sendBroadcast(intent)
        }
    }
}
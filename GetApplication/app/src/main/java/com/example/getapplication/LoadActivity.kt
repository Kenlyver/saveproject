package com.example.getapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getapplication.databinding.ActivityLoadBinding

class LoadActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sendIntent = Intent().apply {
            action = "android.intent.action.LOAD"
        }
        if (sendIntent.resolveActivity(this.packageManager) != null) {
            startActivity(sendIntent)
        }
    }
}
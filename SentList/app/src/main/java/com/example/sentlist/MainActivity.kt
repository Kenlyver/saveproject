package com.example.sentlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sentlist.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val list = arrayListOf(
        "Hello!",
        "Hi!",
        "Salut!",
        "Hallo!",
        "Ciao!",
        "Ahoj!",
        "YAH sahs!",
        "Bog!",
        "Hej!",
        "Czesc!",
        "Ní hảo!",
        "Kon’nichiwa!",
        "Annyeonghaseyo!",
        "Shalom!",
        "Sah-wahd-dee-kah!",
        "Merhaba!",
        "Hujambo!",
        "Olá!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnSentData.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            intent.putExtra("sentList", list)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}
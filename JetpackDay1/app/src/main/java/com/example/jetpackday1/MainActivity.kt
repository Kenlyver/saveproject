package com.example.jetpackday1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackday1.adapter.MainPagerAdapter
import com.example.jetpackday1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vpMain.adapter = MainPagerAdapter(this)
        val tabTittles = arrayOf("One","Two")
        TabLayoutMediator(binding.tabMain, binding.vpMain){tab, position->
            tab.text =tabTittles[position]
        }.attach()

    }
}
package com.example.myservicedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.myservicedemo.databinding.ActivityMainBinding
import com.example.myservicedemo.databinding.FragmentPlayBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PlayFragment>(R.id.frameLayout)
        }
        setContentView(binding.root)
    }
}
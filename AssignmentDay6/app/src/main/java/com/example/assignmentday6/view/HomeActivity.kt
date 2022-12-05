package com.example.assignmentday6.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentday6.databinding.ActivityHomeBinding
import com.example.assignmentday6.viewModel.UserInfoViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        binding.infoViewModel = viewModel
        binding.lifecycleOwner = this
    }
}
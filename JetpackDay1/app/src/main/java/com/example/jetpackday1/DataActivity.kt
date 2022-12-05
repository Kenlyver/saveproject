package com.example.jetpackday1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.jetpackday1.databinding.ActivityDataBinding
import com.example.jetpackday1.viewmodel.DataViewModel

class DataActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDataBinding
    private val viewModel:DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}
package com.example.assignmentday6.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentday6.Interface.LoginResultCallBack
import com.example.assignmentday6.databinding.ActivityMainBinding
import com.example.assignmentday6.viewModel.LoginViewModel
import com.example.assignmentday6.viewModel.LoginViewModelFactory

class MainActivity : AppCompatActivity(), LoginResultCallBack {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm =
            ViewModelProvider(this, LoginViewModelFactory(this)).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this
    }

    override fun onSuccess(message: String) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
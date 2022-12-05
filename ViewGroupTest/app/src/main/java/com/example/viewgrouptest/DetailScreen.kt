package com.example.viewgrouptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.viewgrouptest.databinding.ActivityDetailScreenBinding

class DetailScreen : AppCompatActivity() {
    lateinit var binding: ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle :Bundle ?=intent.extras
        var firstName = bundle!!.getString("firstName")
        var lastName = bundle!!.getString("lastName")
        var userName = bundle!!.getString("userName")
        var age = bundle!!.getString("Age")
        binding.txtFirstName.text =firstName
        binding.txtLastName.text =lastName
        binding.txtUserName.text =userName
        binding.txtAge.text =age

    }
}
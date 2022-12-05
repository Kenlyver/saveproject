package com.example.custompiechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.custompiechart.data.PieData
import com.example.custompiechart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val data = PieData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.add("K", 80.0)
        data.add("E", 15.0)
        data.add("N", 5.0)

        binding.pieChart.setData(data)
    }
}
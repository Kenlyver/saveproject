package com.example.jetpackday1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.work.WorkInfo
import com.example.jetpackday1.databinding.ActivityWorkBinding
import com.example.jetpackday1.viewmodel.WorkViewModel

class WorkActivity : AppCompatActivity() {
    lateinit var binding:ActivityWorkBinding
    private val viewModel:WorkViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener {
            viewModel.enqueueOneTime()
        }
        binding.btnRepeat.setOnClickListener {
            viewModel.enqueuePeriodic()
        }
        viewModel.sampleResult.observe(this,{
            val isSuccess = if(!it.isEmpty() && it[0].state == WorkInfo.State.SUCCEEDED){
              "Work result: ${it[0].outputData.getBoolean("is success",false)}"
            }else{
                "Writting for output"
            }
            binding.tvOutput.text = isSuccess
        })
    }
}
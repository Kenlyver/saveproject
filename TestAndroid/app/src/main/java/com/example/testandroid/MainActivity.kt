package com.example.testandroid

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("MainActivity", "OnCreate")
        val intent = Intent(this, ActivityA::class.java)
        binding.button.setOnClickListener {
            getResult.launch(intent)
        }
        setContentView(binding.root)
        val strBuf = StringBuffer()
        strBuf.append("Task Id : ")
        strBuf.append(this.taskId)
        strBuf.append(" , Activity Id : ")
        strBuf.append(this.toString())

        Log.d("Activity_ID", strBuf.toString())

       binding.btnStartFirst.setOnClickListener {
           val intentFirst = Intent(this, MainActivity::class.java)
           startActivity(intentFirst)
           logActivity()
       }
        logActivity()
    }

    fun logActivity() {
        val result = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = result
            .getRunningTasks(Int.MAX_VALUE)
        Log.d("TopActivity", services[0].topActivity.toString())
        Log.d("BaseActivity", services[0].baseActivity.toString())
        Log.d("NumActivity", services[0].numActivities.toString())
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.getStringExtra("Data")
                Log.d("Messege", value.toString())
            }
        }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "OnStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "OnPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "OnDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "OnRestart")
    }

}
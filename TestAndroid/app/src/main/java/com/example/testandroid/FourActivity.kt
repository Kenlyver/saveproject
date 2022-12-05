package com.example.testandroid

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.testandroid.databinding.ActivityFourBinding

class FourActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourBinding.inflate(layoutInflater)
        val strBuf = StringBuffer()
        strBuf.append("Task Id : ")
        strBuf.append(this.taskId)
        strBuf.append(" , Activity Id : ")
        strBuf.append(this.toString())
        Log.d("FourActivity",strBuf.toString())
        val result = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = result
            .getRunningTasks(Int.MAX_VALUE)
        Log.d("TopActivity",services[0].topActivity.toString())
        Log.d("BaseActivity",services[0].baseActivity.toString())
        Log.d("NumActivity",services[0].numActivities.toString())
        binding.btnNext.setOnClickListener {
            val intentNext = Intent(this,MainActivity::class.java)
            startActivity(intentNext)
        }
        binding.btnThis.setOnClickListener {
            val intentNext = Intent(this,FourActivity::class.java)
            startActivity(intentNext)
        }
        binding.btnActivityThird.setOnClickListener {
            val intentNext = Intent(this,ThirdActivity::class.java)
            startActivity(intentNext)
        }
        binding.btnActivityTwo.setOnClickListener {
            val intentNext = Intent(this,ActivityA::class.java)
            startActivity(intentNext)
        }
        setContentView(binding.root)
    }
}
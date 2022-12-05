package com.example.testandroid

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroid.databinding.ActivityActivityBinding


class ActivityA : AppCompatActivity() {
    private lateinit var binding:ActivityActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        Log.d("ActivityA","OnCreate")
        val intent = Intent().putExtra("Data","Data is send from ActivityA")
        setResult(RESULT_OK,intent)
        val strBuf = StringBuffer()
        strBuf.append("Task Id : ")
        strBuf.append(this.taskId)
        strBuf.append("Top Activity : ")
        strBuf.append(this)
        strBuf.append(" , Activity Id : ")
        strBuf.append(this.toString())
        Log.d("SecondActivity",strBuf.toString())
        binding.btnNext.setOnClickListener {
            val intentNext = Intent(this,ThirdActivity::class.java)
            startActivity(intentNext)
        }
        binding.btnThis.setOnClickListener {
            val intentNext = Intent(this,ActivityA::class.java)
            startActivity(intentNext)
        }
        binding.btnActivityOne.setOnClickListener {
            val intentNext = Intent(this,MainActivity::class.java)
            startActivity(intentNext)
        }
        binding.btnActivityFour.setOnClickListener {0
            val intentNext = Intent(this,FourActivity::class.java)
            startActivity(intentNext)
        }
        val result = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = result
            .getRunningTasks(Int.MAX_VALUE)
        Log.d("TopActivity",services[0].topActivity.toString())
        Log.d("BaseActivity",services[0].baseActivity.toString())
        Log.d("NumActivity",services[0].numActivities.toString())
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()
        Log.d("ActivityA","OnStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityA","OnPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityA","OnDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityA","OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ActivityA","OnRestart")
    }
}
package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import java.util.*

class MyService : Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Toast.makeText(this,"Service started.",Toast.LENGTH_SHORT).show()

        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 5000)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Service destroyed.",Toast.LENGTH_SHORT).show()
        mHandler.removeCallbacks(mRunnable)
    }

    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        Toast.makeText(this,"Random Number : $number",Toast.LENGTH_SHORT).show()
        mHandler.postDelayed(mRunnable, 5000)
    }

}
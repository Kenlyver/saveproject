package com.example.myservicedemo

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat

class CalculatorService : Service() {

    companion object {
        const val CALCULATOR = "calculator"
        const val DATA = "data"

        fun startService(context: Context, a: Int, cal: String, b: Int) {
            val startIntent = Intent(context, CalculatorService::class.java)
            startIntent.putExtra("a", a)
            startIntent.putExtra("cal", cal)
            startIntent.putExtra("b", b)
            ContextCompat.startForegroundService(context, startIntent)
        }

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val a = intent?.getIntExtra("a", 0)
        val cal = intent?.getStringExtra("cal")
        val b = intent?.getIntExtra("b", 0)
        val result = calculate(a!!, cal.toString(), b!!)
        Log.d("result", result.toString())
        val intent = Intent(CALCULATOR)
        intent.putExtra(DATA, result)
        sendBroadcast(intent)
        return START_NOT_STICKY
    }

    fun calculate(a: Int, b: String, c: Int): Int? =
        when (b) {
            "+" -> a + c
            "-" -> a - c
            "*" -> a * c
            "/" -> a / c
            else -> null
        }
}
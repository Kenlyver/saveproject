package com.example.jetpackday1.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class SampleWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        return try{
            val link= inputData.getString("link")!!
            downloadFromNetwork(link)
            val workResult = workDataOf("is success" to true)
            Result.success(workResult)
        }catch (e:Exception){
            val workResult = workDataOf("is success" to false)
            Result.failure(workResult)
        }
    }
    fun downloadFromNetwork(link:String){
        Log.d("test","Download file from :$link")
    }
}
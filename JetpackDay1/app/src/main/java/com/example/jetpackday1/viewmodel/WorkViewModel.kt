package com.example.jetpackday1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.*
import com.example.jetpackday1.worker.SampleWorker
import java.util.concurrent.TimeUnit

class WorkViewModel(app: Application):AndroidViewModel(app) {
    private val workManager = WorkManager.getInstance(app.applicationContext)
    val sampleResult = workManager.getWorkInfosByTagLiveData("GAM")

    fun enqueueOneTime(){
//        val workRequest = OneTimeWorkRequest.from(SampleWorker::class.java)
        val workBuilder= OneTimeWorkRequestBuilder<SampleWorker>()
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
        workBuilder.setConstraints(constraint.build())

        val workData = workDataOf("link" to "http://google.com","url" to "http://fsoft.com.vn")
        workBuilder.setInputData(workData)
        val dataBuilder = Data.Builder()
            .putString("link","http://google.com")
            .putAll(workData)
        workBuilder.addTag("GAM")
        workBuilder.setInputData(dataBuilder.build())
        val workRequest=workBuilder.build()
//        workManager.enqueue(workRequest)
        workManager.enqueueUniqueWork("FSOFT",ExistingWorkPolicy.REPLACE,workRequest)
    }
    fun enqueuePeriodic(){
        // moi request phai cach nhau 15ph
        val request = PeriodicWorkRequestBuilder<SampleWorker>(1,TimeUnit.HOURS)
        //set constains same above
        workManager.enqueueUniquePeriodicWork("SAMPLE",ExistingPeriodicWorkPolicy.REPLACE,request.build())
    }
}
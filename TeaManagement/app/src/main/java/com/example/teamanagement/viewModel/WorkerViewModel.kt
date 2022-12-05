package com.example.teamanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.teamanagement.worker.TeaWorker
import java.util.concurrent.TimeUnit

class WorkerViewModel(app: Application) : AndroidViewModel(app) {
    private val workManager = WorkManager.getInstance(app.applicationContext)


    fun enqueuePeriodic() {
        val work = PeriodicWorkRequestBuilder<TeaWorker>(1, TimeUnit.DAYS)
        workManager.enqueueUniquePeriodicWork(
            "NOTIFY",
            ExistingPeriodicWorkPolicy.REPLACE,
            work.build()
        )
    }
}
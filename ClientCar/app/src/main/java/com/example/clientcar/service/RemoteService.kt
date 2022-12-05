package com.example.clientcar.service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.clientcar.AIDLConfig
import com.example.clientcar.model.DataTPMS
import com.example.clientcar.source.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RemoteService: LifecycleService() {
    private lateinit var repository: AppRepository
    private val job = CoroutineScope(Dispatchers.IO)
    private val actionSent = MutableLiveData<String>()
    private val data = arrayListOf<DataTPMS>()

    private val binder = object: AIDLConfig.Stub(){
        override fun sendAction(Action: String?) {
            actionSent.postValue(Action!!)
        }

        override fun insertData(data: DataTPMS?) {
            job.launch {
                data?.copy(id = null)?.let {
                    repository.insertData(it)
                }
            }
        }

        override fun updateData(data: DataTPMS?) {
            job.launch {
                if (data != null) {
                    repository.updateData(data)
                }
            }
        }

        override fun deleteData(data: DataTPMS?) {
            job.launch {
                data?.let { repository.deleteData(it) }
            }
        }
    }
    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return binder
    }
    override fun onCreate() {
        super.onCreate()
        repository = AppRepository.getInstance(this.applicationContext)
        actionSent.observe(this) { action ->
            repository.getData().observe(this@RemoteService) {
                Intent(action).apply {
                    putParcelableArrayListExtra("DATA", it as ArrayList<DataTPMS>)
                    sendBroadcast(this)
                }
                Log.d("Test", it.toString())
                data.clear()
                data.addAll(it)
            }
        }
    }
}
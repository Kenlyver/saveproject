package com.example.serverstudentapp.service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.serverstudentapp.ServerAIDL
import com.example.serverstudentapp.model.Student
import com.example.serverstudentapp.source.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteService : LifecycleService() {

    private lateinit var repository: StudentRepository
    private val job = CoroutineScope(Dispatchers.IO)
    private val actionSent = MutableLiveData<String>()
    private val student = arrayListOf<Student>()

    private val binder = object : ServerAIDL.Stub() {
        override fun sendAction(action: String?) {
            actionSent.postValue(action!!)
        }

        override fun insertStudent(s: Student?) {
            job.launch {
                s?.copy(id = null)?.let {
                    repository.insertStudent(it)
                }
            }
        }

        override fun updateStudent(s: Student?) {
            job.launch {
                if (s != null) {
                    repository.updateStudent(s)
                }
            }
        }

        override fun deleteStudent(s: Student?) {
            job.launch {
                if (s != null) {
                    repository.deleteStudent(s)
                }
            }
        }

        override fun findStudent(name: String?): MutableList<Student> {
            return student.filter {
                it.name.lowercase().indexOf(name.toString()) != -1
            }.toMutableList()
        }

        override fun sortStudent(type: Int): MutableList<Student> {
            val temp = arrayListOf<Student>()
            temp.addAll(student)
            if (type == 1) {
                temp.sortBy { it.run { (mathP + physicP + englishP) / 3 } }
            } else {
                temp.sortByDescending { it.run { (mathP + physicP + englishP) / 3 } }
            }
            return temp
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        repository = StudentRepository.getInstance(this.applicationContext)
        actionSent.observe(this) { action ->
            repository.getAllStudent().observe(this@RemoteService) {
                Intent(action).apply {
                    putParcelableArrayListExtra("DATA", it as ArrayList<Student>)
                    sendBroadcast(this)
                }
                Log.d("Test", it.toString())
                student.clear()
                student.addAll(it)
            }
        }
    }
}
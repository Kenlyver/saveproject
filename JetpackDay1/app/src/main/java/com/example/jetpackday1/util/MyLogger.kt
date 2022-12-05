package com.example.jetpackday1.util

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLogger:LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logOnCreate(){
        Log.d("test","onCreate called")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logOnStart(){
        Log.d("test","onStart called")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logOnResume(){
        Log.d("test","onResume called")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logOnPause(){
        Log.d("test","onPause called")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logOnStop(){
        Log.d("test","onStop called")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logOnDestroy(){
        Log.d("test","onDestroy called")
    }
}
package com.example.serverstudent.base

@Suppress("UNUSED")
interface CallbackProvider<T> {
    val callbacks: ArrayList<T>

    fun addCallback(callback:T){
        callbacks.add(callback)
    }
    fun removeCallback(callback:T){
        callbacks.remove(callback)
    }
}
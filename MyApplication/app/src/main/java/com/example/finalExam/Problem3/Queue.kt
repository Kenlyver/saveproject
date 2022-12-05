package com.example.finalExam

interface Queue<T> {
    fun enqueue(element:T):Boolean
    fun dequeue():T?
    fun peek():T?
    val count:Int
    val isEmpty:Boolean

}
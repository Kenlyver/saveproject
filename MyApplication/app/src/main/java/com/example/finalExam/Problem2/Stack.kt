package com.example.finalExam

interface Stack<i:Int> {

    fun peek():Int?
    fun put(e: Int)

    fun pop(): Int?

    val count:Int
    val isEmpty:Boolean
        get() = count==0
}

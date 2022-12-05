package com.example.finalExam

class QueuePro<T> : Queue<T> {
    private val saveQueue = arrayListOf<T>()

    override fun enqueue(element: T): Boolean {
        return saveQueue.add(element)
    }

    override fun dequeue(): T? {
        return if (isEmpty) null
        else saveQueue.removeAt(0)
    }


    override fun toString(): String = saveQueue.toString()

    override val count: Int
        get() = saveQueue.size

    override fun peek(): T? = saveQueue.getOrNull(0)
    override val isEmpty: Boolean
        get() = count == 0
}


fun main() {

    val q = QueuePro<Char>()

    fun QueuePro<Char>.isEmpty():Boolean{
        if(q.isEmpty) return true else return false
    }
    println(q.isEmpty())
    q.enqueue('A')
    println(q.isEmpty())
    q.enqueue('B')
    q.enqueue('C')
    q.peek()
    q.peek()
    q.dequeue()
    q.dequeue()
    q.dequeue()
    val checkDequeue = q.dequeue()
    if (checkDequeue != null) {
        println("$checkDequeue")
    }else println("null")
}
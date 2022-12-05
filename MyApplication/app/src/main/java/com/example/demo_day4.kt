package com.example


object Algorithm {
    fun <T> countIf(c: Collection<T>, p: UnaryPredicate<T>): Int {
        var count = 0
        for (elem in c) if (p.test(elem)) ++count
        return count
    }
}

interface UnaryPredicate<T> {
    fun test(obj: T): Boolean
}

class isEven:UnaryPredicate<Int?>{
    override fun test(obj: Int?): Boolean {
        return obj!! % 2 ==0
    }
}

fun task1(){
    var arr:Collection<Int> = mutableListOf(99,15,3,20,41,90,21,10)
    for(ele in arr){
        print("$ele ")
    }
    println()
    var count = Algorithm.countIf(arr, isEven())
    println("The number is even in array: $count")
}

object swapArray {
    fun <T> swap(a: Array<T>, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }
}
fun task2(){
    var arr= arrayOf(99,15,3,27,41,90,21,10)
    print("Before swap: ")
    for (ele in arr){
        print("$ele ")
    }
    println()
    swapArray.swap(arr,6,7)
    print("After swap: ")
    for (ele in arr){
        print("$ele ")
    }
}

object searchMax {
    fun <T> max(list: List<T>, begin: Int, end: Int): T where T : Any?, T : Comparable<T>? {
        var begin = begin
        var maxElem = list[begin]
        ++begin
        while (begin < end) {
            if (maxElem!!.compareTo(list[begin]) < 0) maxElem = list[begin]
            ++begin
        }
        return maxElem
    }
}

fun task3() {
    var list = listOf(1,2,41,5,6,12)
    println(searchMax.max(list,1,3))
}


fun main(){
    task1()
    task2()
    task3()
}



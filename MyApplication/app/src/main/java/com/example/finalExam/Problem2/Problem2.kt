package com.example.finalExam

class StackPro<i:Int>:Stack<Int>{
    private var saveParam = arrayListOf<Int>()

    override fun put(e: Int) {
        saveParam.add(e)
    }
    override fun pop(): Int? {
        return saveParam.removeLastOrNull()
    }
    override val count: Int
        get() = saveParam.size

    override fun peek(): Int? {
        return saveParam.lastOrNull()
    }

}

fun main(){
    val stack=StackPro<Int>()
    stack.put(1)
    stack.put(2)
    stack.pop()
    stack.pop()
    val checkPop = stack.pop()
    if (checkPop != null) {
        println("$checkPop")
    }else println("null")
}
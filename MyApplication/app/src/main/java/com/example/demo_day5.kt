package com.example

data class Student(val stdId:Int, val stdName:String, val stdAge:Int)

data class Point(val x:Int =0, val y:Int = 10){
    operator fun plus(p:Point):Point{
        return Point(x+p.x,y+p.y)
    }
}

fun isString(s:String) = s== "SangBX" || s == "ABC"|| s=="XYZ"
val predicate:(String)->Boolean = ::isString

fun main(){
    val student = Student(1,"SangBX",24)
    var id1 = student.stdId
    val (id, name, age) = student
    println(id)
    println(name)
    println(age)
    val programs = mutableListOf("Kotlin","Java","C++","React")
    programs.add("C#")
    programs.remove("C#")
    println(programs)

   println((1..10).filter{
       it%2==0
   })

    val list:List<Any> = listOf("SangBX",12,"ABC",20.1,"Kotlin","Type Check")
    for(value in list){
        if(value is String){
            println("String: $value of lenght ${value.length}")
        }
    }

    val p1=Point(3,2)
    val p2= Point(3,5)
    var sum = Point()
    sum = p1 + p2
    println("Sum=($sum.x),(${sum.y})")

    println(predicate("SangBX"))
}
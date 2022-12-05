import java.math.BigDecimal
import java.util.*

fun Ex1(){
    for(x in 10..200){
        if(x%7==0 && x%5!=0){
            print("$x,")
        }
    }
}

fun Ex2(){
    var x=0
    fun inp(){
        val reader= Scanner(System.`in`)
        print("Enter a number: ")
        x = reader.nextInt()
    }
    fun convert(){
        if(x in 10..99) {
            var y: String = Integer.toBinaryString(x)
            var z: String = Integer.toHexString(x)
            print("Binary:" + y + " Hexadecimal:" + z)
        }
        else{
            println("X is two-digit integer number")
            inp()
            convert()
        }
    }
    inp()
    convert()
}

fun Ex3(){
    var arr= arrayOf(99,15,3,27,41,90,21,10)
    println(Arrays.toString(arr))
    println(Arrays.toString(arr.sortedArray()))
}

fun Ex4(){
    var text:String
    var newText:String
    print("Enter a String: ")
    text = readLine()!!
    val count = text.trim().split("\\s".toRegex()).size
    println(count)
    val arrWord = text.split(".")
    for (word in arrWord) {
        word.capitalize()
    }

    println(text.capitalize())
}


fun Ex5(){
    var month:Int=0
    var year:Int=0
    fun inp() {
        val reader = Scanner(System.`in`)
        print("Enter the month: ")
        month = reader.nextInt()
        print("Enter the year: ")
        year = reader.nextInt()
    }
    fun check(){
        if(month in 1..12 && year>0){
            if (month == 1 || month == 3 ||month == 5 ||month == 7 ||month == 8 ||month == 10 ||month == 12)
                println("The numbers of days that month is 31")
            else if (month==2 && year%400==0 || (year%4==0 && year%100!=0)){
                println("The numbers of days that month is 29")
            }
            else if (month==2){
                println("The numbers of days that month is 28")
            }
            else println("The numbers of days that month is 30")
        }
        else{
            println("Enter month and year again")
            inp()
            check()
        }
    }
    inp()
    check()
}

fun main(){
//    println("Task1")
//    Ex1()
//    println("Task2")
//    Ex2()
//    println("Task3")
//    Ex3()
    println("Task4")
    Ex4()
//    println("Task5")
//    Ex5()
}
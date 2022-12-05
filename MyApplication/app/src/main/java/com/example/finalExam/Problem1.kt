package com.example

fun palindrome(input:String):Boolean{
    var tempString = ""
    var inpLength = input.length
    for(i in inpLength-1 downTo 0){
        tempString += input.get(i)
    }
    if (input.trim().equals(tempString.trim())){
        println("true")
        return true
    }
    else {
        println("false")
        return false
    }
}


fun main(){
    palindrome("abba")
    palindrome("abcdefg")
    palindrome(" abba")
    palindrome("abb a")
}
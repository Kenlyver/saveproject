import android.R.string
import java.lang.StringBuilder
import java.util.*


fun inp():Int{
    var x=0
    val reader= Scanner(System.`in`)
    print("Enter a number: ")
    x = reader.nextInt()
    return x
}

fun inpHex_Str():String{
    var hex_str = ""
    print("Enter a hexadecimal string: ")
    hex_str = readLine()!!.toString()
    return hex_str
}
fun checkHex(hex:String):Boolean{
    var isHex=false
    for(char in hex){
        if(((char>='0')&&(char<='9')) || ((char>='A')&&(char<='F')) || ((char>='a')&&(char<='f')))
            isHex = true
        break
    }
    return isHex
}

fun taskOne(){
    var x = inp()
    var z: String = Integer.toHexString(x)
    println("Integer value to a hexadecimal string is:" + z)
}
fun String.toBinString():String
{
    var i =0
    var conv_Binary = ""
    if (checkHex(this)){
        while (i<this.length){
            when(this[i]){
                '0'->conv_Binary+="0000"
                '1'->conv_Binary+="0001"
                '2'->conv_Binary+="0010"
                '3'->conv_Binary+="0011"
                '4'->conv_Binary+="0100"
                '5'->conv_Binary+="0101"
                '6'->conv_Binary+="0110"
                '7'->conv_Binary+="0111"
                '8'->conv_Binary+="1000"
                '9'->conv_Binary+="1001"
                'a', 'A'->conv_Binary+="1010"
                'b', 'B'->conv_Binary+="1011"
                'c', 'C'->conv_Binary+="1100"
                'd', 'D'->conv_Binary+="1101"
                'e', 'E'->conv_Binary+="1110"
                'f', 'F'->conv_Binary+="1111"
            }
            i++
        }
    }else {
        println("Enter hexadecimal again")
        inpHex_Str().toBinString()
}
    return conv_Binary
}
fun taskTwo(){
    var hex:String = inpHex_Str()
    println("to Binary: "+hex.toBinString())
}

fun taskFour(){
    var x1=0
    var x2=1
    for (index in 1..20) {
        print("$x1  ")
        val sum = x1 + x2
        x1 = x2
        x2 = sum
    }

}

val integerChars = '0'..'9'

fun isNumber(input: String): Boolean {
    var dotOccurred = 0
    return input.all { it in integerChars || it == '.' && dotOccurred++ < 1 }
}
fun inpCardNumber():String{
    var cardNum = ""
    print("Enter a card number: ")
    cardNum = readLine()!!
    if(!((cardNum.length>=12 && cardNum.length<=19) && isNumber(cardNum))){
        println("Please enter number card again")
        inpCardNumber()
    }
    return cardNum
}
fun checkCardType(cardNum: String){
    if(cardNum.startsWith("4")) println("VISA card")
    else if((cardNum.startsWith("50") && cardNum.endsWith("69")) ||
        (cardNum.startsWith("2221") && cardNum.endsWith("2720"))) println("Master Card")
    else if (cardNum.startsWith("3528") && cardNum.endsWith("3589")) println("JCB Card")
    else println("Other")
}
fun taskFive(){
    var inpCard:String = inpCardNumber()
   checkCardType(inpCard)
}


fun turnOffB2b3(hex: String):String{
    var bin = StringBuilder(Integer.toBinaryString(hex.toInt(16)).padStart(Int.SIZE_BITS,'0'))
    bin[13]='0'
    var dec = Integer.parseInt(bin.toString(),2)
    return Integer.toHexString(dec).toString()
}

fun taskThree(){
    var hex:String = inpHex_Str()
    println("result:"+ turnOffB2b3(hex))
}


fun main(){
//    taskOne()
//    taskTwo()
//    taskThree()
//    taskFour()
    taskFive()
}

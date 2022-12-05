package com.example.JUnitTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Calculator{
    fun add(x:Int,y:Int):Int{
        return x+y
    }
}

class demo_JUnit {

    private val calculator = Calculator()

    @Test
    @DisplayName("1+2=3")
    fun whenAdding1and3_thenAnswerIs4() {
        Assertions.assertEquals(4, calculator.add(1, 3))
    }
}

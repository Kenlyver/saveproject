package com.example

enum class Orientation {
    NORTH, EAST, SOUTH, WEST
}

data class GridPosition(val x: Int, val y: Int)

class Robot(var gridPosition: GridPosition = GridPosition(0, 0), var orientation: Orientation = Orientation.NORTH) {
    fun turnRight() {
        val values = Orientation.values()
        orientation = values[(orientation.ordinal + 1) % values.size]
    }
    fun turnLeft() {
        val values = Orientation.values()
        orientation = values[(orientation.ordinal - 1 + values.size) % values.size]
    }
    fun advance() {
        when (orientation) {
            Orientation.NORTH -> gridPosition = GridPosition(gridPosition.x, gridPosition.y + 1)
            Orientation.EAST -> gridPosition = GridPosition(gridPosition.x + 1, gridPosition.y)
            Orientation.SOUTH -> gridPosition = GridPosition(gridPosition.x, gridPosition.y - 1)
            Orientation.WEST -> gridPosition = GridPosition(gridPosition.x - 1, gridPosition.y)
        }
    }
    fun simulate(instructions: String) {
        instructions.forEach {
            when (it) {
                'R' -> turnRight()
                'L' -> turnLeft()
                'A' -> advance()
            }
        }
        print(gridPosition)
        println(orientation)
    }
}


fun main(){
    Robot(GridPosition(7,3),Orientation.NORTH).simulate("RAALAL")
}
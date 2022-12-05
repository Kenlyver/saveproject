package com.example.jetpackday1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="col_id")
    val id:Int?,
    @ColumnInfo(name ="col_name")
    val name:String,
    @ColumnInfo(name ="col_class")
    val className:String,
    @ColumnInfo(name ="col_address")
    val address:String,
    @ColumnInfo(name ="col_score")
    val score:Float
)

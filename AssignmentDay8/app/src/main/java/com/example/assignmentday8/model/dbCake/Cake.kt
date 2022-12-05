package com.example.assignmentday8.model.dbCake

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cake")
data class Cake(
    @ColumnInfo(name = "col_id")
    @PrimaryKey(autoGenerate = false)
    val Id:Int?,
    @ColumnInfo(name = "col_amount")
    val Amount: Int,
    @ColumnInfo(name = "col_price")
    val Price: String
)

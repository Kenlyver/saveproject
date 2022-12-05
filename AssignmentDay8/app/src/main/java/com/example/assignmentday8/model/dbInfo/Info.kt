package com.example.assignmentday8.model.dbInfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_info")
data class Info(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id: Int?,
    @ColumnInfo(name = "col_holderName")
    val holderName:String,
    @ColumnInfo(name = "col_amount")
    val amount:Int,
    @ColumnInfo(name = "col_transactionType")
    val transactionType:String,
    @ColumnInfo(name = "col_currency")
    val currency: String,
    @ColumnInfo(name = "col_dateTime")
    val dateTime:String
)
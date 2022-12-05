package com.example.assignmentday8.model.dbWallet

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_wallet")
data class Wallet(
    @ColumnInfo(name = "col_id")
    @PrimaryKey(autoGenerate = false)
    val Id: Int?,
    @ColumnInfo(name = "col_vnd")
    val vnd:String,
    @ColumnInfo(name = "col_usd")
    val usd:String
)
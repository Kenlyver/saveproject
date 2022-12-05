package com.example.serverorder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id: Int? = null,
    @ColumnInfo(name = "col_name")
    val name: String
) : Serializable

package com.example.serverorder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_order")
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id: Int? = null,
    @ColumnInfo(name = "col_idCustomer")
    val idCustomer: Int,
    @ColumnInfo(name = "col_time")
    val timestamp: String? = null,
    @ColumnInfo(name = "col_value")
    val value: Int
) : Serializable

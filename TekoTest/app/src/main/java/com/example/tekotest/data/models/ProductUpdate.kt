package com.example.tekotest.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "update_save")
data class ProductUpdate(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "col_error")
    val errorDescription: String,
    @ColumnInfo(name = "col_name")
    val name: String,
    @ColumnInfo(name = "col_sku")
    val sku: Long,
    @ColumnInfo(name = "col_img")
    val image: String,
    @ColumnInfo(name = "col_color")
    var color: String
)

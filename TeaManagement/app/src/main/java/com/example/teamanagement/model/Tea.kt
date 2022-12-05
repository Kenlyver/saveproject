package com.example.teamanagement.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_tea")
data class Tea(
    @ColumnInfo(name = "col_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "col_name")
    val name: String,
    @ColumnInfo(name = "col_description")
    val description: String,
    @ColumnInfo(name = "col_origin")
    val origin: String,
    @ColumnInfo(name = "col_ingredients")
    val ingredients: String,
    @ColumnInfo(name = "col_steepTime")
    val steepTime: String,
    @ColumnInfo(name = "col_caffeineLevel")
    val caffeineLevel: String
) : Parcelable

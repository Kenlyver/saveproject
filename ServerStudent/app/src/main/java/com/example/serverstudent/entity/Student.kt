package com.example.serverstudent.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id:Int?=0,
    @ColumnInfo(name = "col_name")
    val name:String,
    @ColumnInfo(name = "col_age")
    val age:Int,
    @ColumnInfo(name = "col_mathP")
    val mathP:String,
    @ColumnInfo(name = "col_physicP")
    val physicP:String,
    @ColumnInfo(name = "col_EnglishP")
    val englishP:String
):Parcelable

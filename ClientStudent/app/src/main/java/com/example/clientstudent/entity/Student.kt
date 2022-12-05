package com.example.serverstudent.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    val id:Int?=0,
    val name:String,
    val age:Int,
    val mathP:String,
    val physicP:String,
    val englishP:String
):Parcelable

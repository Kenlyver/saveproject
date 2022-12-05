package com.example.serverstudent.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StudentResponse(
    @ResponseCode val responseCode:Int,
    val idStudent:Int?,
    val students:List<Student>
):Parcelable
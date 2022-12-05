package com.example.serverstudentapp.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Student(
    val id: Int? = 0,
    val name: String,
    val age: Int,
    val mathP: Float,
    val physicP: Float,
    val englishP: Float
) : Parcelable, Serializable {
    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(source: Parcel): Student {
            return Student(source)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        id?.let { dest?.writeInt(it) }
        dest?.writeString(name)
        dest?.writeInt(age)
        dest?.writeFloat(mathP)
        dest?.writeFloat(physicP)
        dest?.writeFloat(englishP)
    }

    override fun describeContents(): Int {
        return 0
    }

}
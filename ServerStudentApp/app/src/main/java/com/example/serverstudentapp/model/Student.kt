package com.example.serverstudentapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id: Int? = 0,
    @ColumnInfo(name = "col_name")
    val name: String,
    @ColumnInfo(name = "col_age")
    val age: Int,
    @ColumnInfo(name = "col_mathP")
    val mathP: Float,
    @ColumnInfo(name = "col_physicP")
    val physicP: Float,
    @ColumnInfo(name = "col_englishP")
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
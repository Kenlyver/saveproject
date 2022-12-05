package com.example.clientcar.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class DataTPMS(
    val id: Int? = 0,
    val topLeftPressure: Float,
    val bottomLeftPressure: Float,
    val topRightPressure: Float,
    val bottomRightPressure: Float,
    val topLeftTemperature: Float,
    val bottomLeftTemperature: Float,
    val topRightTemperature: Float,
    val bottomRightTemperature: Float
) : Parcelable, Serializable {
    companion object CREATOR : Parcelable.Creator<DataTPMS> {
        override fun createFromParcel(source: Parcel): DataTPMS {
            return DataTPMS(source)
        }

        override fun newArray(size: Int): Array<DataTPMS?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (id != null) {
            dest?.writeInt(id)
        }
        dest?.writeFloat(topLeftPressure)
        dest?.writeFloat(bottomLeftPressure)
        dest?.writeFloat(topRightPressure)
        dest?.writeFloat(bottomRightPressure)
        dest?.writeFloat(topLeftTemperature)
        dest?.writeFloat(bottomLeftTemperature)
        dest?.writeFloat(topRightTemperature)
        dest?.writeFloat(bottomRightTemperature)
    }

    override fun describeContents(): Int {
        return 0
    }
}

package com.example.clientcar.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DataTPMS(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")
    val id: Int? = 0,
    @ColumnInfo(name = "col_TLPressure")
    val topLeftPressure: Float,
    @ColumnInfo(name = "col_BLPressure")
    val bottomLeftPressure: Float,
    @ColumnInfo(name = "col_TRPressure")
    val topRightPressure: Float,
    @ColumnInfo(name = "col_BRPressure")
    val bottomRightPressure: Float,
    @ColumnInfo(name = "col_TLTemperature")
    val topLeftTemperature: Float,
    @ColumnInfo(name = "col_BLTemperature")
    val bottomLeftTemperature: Float,
    @ColumnInfo(name = "col_TRTemperature")
    val topRightTemperature: Float,
    @ColumnInfo(name = "col_BRTemperature")
    val bottomRightTemperature: Float,
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

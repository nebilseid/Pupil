package com.bridge.androidtechnicaltest.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Pupils")
@Parcelize
data class Pupil(
        @PrimaryKey
        @ColumnInfo(name = "pupil_id")
        val pupilId: Long,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "country")
        val country: String,

        @ColumnInfo(name = "image")
        val image: String,

        @ColumnInfo(name = "latitude")
        val latitude: Double,

        @ColumnInfo(name = "longitude")
        val longitude: Double
) : Parcelable

class PupilList(
        val items: MutableList<Pupil>
)
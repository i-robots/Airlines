package com.example.airlines.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Plane (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "plane_no")
    val planeNo: Int,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="No_of_seats")
    val noOfSeats:Int,

    @ColumnInfo(name="quality")
    val quality:String

):Serializable{
    override fun toString() = name
}
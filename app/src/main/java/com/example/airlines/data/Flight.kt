package com.example.airlines.data


import androidx.room.*
import java.io.Serializable

@Entity(tableName = "flight")
data class Flight(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "flight_no")
    val flightNo: Int,

    @ColumnInfo(name="root")
    val root: String,

    @ColumnInfo(name="dest")
    val dest:String,

    @ColumnInfo(name ="flight_duration")
    val flightDuration: String,

    @ColumnInfo(name="flight_expense")
    val flightExpense: Int,

    @Embedded
    val plane: Plane

):Serializable
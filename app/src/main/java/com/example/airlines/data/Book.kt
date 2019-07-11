package com.example.airlines.data
import androidx.room.*
import java.io.Serializable

@Entity(tableName = "book")
data class Book (
    @PrimaryKey @ColumnInfo(name="ticketNo")
    val ticketNo: Int,

    @Embedded
    val flight : Flight,

    @ColumnInfo(name="ways")
    val noOfWays: Int,

    @ColumnInfo(name = "no_pass")
    val noOfPass: Int

):Serializable

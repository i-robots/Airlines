package com.example.airlines.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Continent(
    @PrimaryKey @ColumnInfo(name="continent_name")
    val contientName: String
)
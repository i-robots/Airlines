package com.example.airlines.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class State(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="state_id")
    val stateId:Int,

    @ColumnInfo(name="state_name")
    val stateName: String,

    @Embedded
    val continent: Continent
)
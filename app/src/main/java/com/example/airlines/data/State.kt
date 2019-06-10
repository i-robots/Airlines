package com.example.airlines.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class State(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="state_id")
    private val stateId:Int,

    @ColumnInfo(name="state_name")
    private val  stateName: String,

    @Embedded
    private val continent: Continent
)
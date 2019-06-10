package com.example.airlines.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="Customer")
data class Customer(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "customer_id")
    val id: Long,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="gender")
    val gender: String,

    @ColumnInfo(name="nationality")
    val nationality: String,

    @ColumnInfo(name="date_of_birth")
    val dob: String

):Serializable
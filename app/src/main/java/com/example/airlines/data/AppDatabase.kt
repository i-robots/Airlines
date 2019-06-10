package com.example.airlines.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Customer::class,Plane::class,Flight::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun customerDao():CustomerDao
    abstract fun flightDao():FlightDao
    abstract fun planeDao():PlaneDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                     AppDatabase::class.java,
                    "application_database"
                ).build()

                INSTANCE = instance
                return  instance
            }
        }
    }
}
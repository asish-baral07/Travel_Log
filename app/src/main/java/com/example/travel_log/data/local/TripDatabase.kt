package com.example.travel_log.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TripEntity::class], version = 1)
@TypeConverters(Converters::class)

abstract class TripDatabase: RoomDatabase(){
    abstract fun tripDao(): TripDao
}

package com.example.travel_log.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TripEntity::class], version = 1)
@TypeConverters(Converters::class)
/* @TypeConverters - used when room cannot store custom data types directly,
                     Used to convert unsupported data types into supported database types
*/
abstract class TripDatabase: RoomDatabase(){
    abstract fun tripDao(): TripDao
}

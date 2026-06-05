package com.example.travel_log.data.local

import androidx.room.TypeConverter
import com.example.travel_log.data.model.TripType

class Converters {
    @TypeConverter
    fun fromTripType(type: TripType): String {
        return type.name
    }
    @TypeConverter
    fun toTripType(value: String): TripType {
        return TripType.valueOf(value)
    }
}
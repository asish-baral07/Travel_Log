package com.example.travel_log.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travel_log.data.model.TripType

@Entity(tableName = "trip_table")
data class TripEntity(
    @PrimaryKey
    val countryCode : String,
    val countryName : String,
    val capital: String,
    val region: String?,
    val flag: String?,
    val type: TripType // planned OR wishlist

)

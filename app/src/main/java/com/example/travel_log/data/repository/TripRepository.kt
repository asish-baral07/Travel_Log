package com.example.travel_log.data.repository

import com.example.travel_log.data.local.TripDao
import com.example.travel_log.data.local.TripEntity
import javax.inject.Inject

class TripRepository @Inject constructor(
    private val dao: TripDao
) {
    suspend fun insertTrip(trip: TripEntity) {
        dao.insertTrip(trip)
    }
    fun getPlannedTrips() = dao.getPlannedTrips()
    fun getWishlistTrips() = dao.getWishlistTrips()
    fun getVisitedTrips() = dao.getVisitedTrips()
    suspend fun deleteTrip(code : String){
        dao.deleteTrip(code)
    }
    fun isWishlist(code: String) = dao.isWishList(code)
    suspend fun removeWishlist(code: String){
        dao.removeWishlist(code)
    }
}

package com.example.travel_log.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip : TripEntity)

    @Query("SELECT * FROM trip_table WHERE type = 'PLANNED'")
    fun getPlannedTrips(): Flow<List<TripEntity>>
    // flow means Observe database continuously , if data changes, UI updates automatically

    @Query("SELECT * FROM trip_table WHERE type = 'WISHLIST'")
    fun getWishlistTrips(): Flow<List<TripEntity>>

    @Query("SELECT * FROM trip_table WHERE type = 'VISITED'")
    fun getVisitedTrips(): Flow<List<TripEntity>>

    @Query("DELETE FROM trip_table WHERE countryCode = :code")
    suspend fun deleteTrip(code : String)


    @Query("SELECT EXISTS(SELECT 1 FROM trip_table WHERE countryCode = :code AND type = 'WISHLIST')")
    fun isWishList(code: String): Flow<Boolean>

    @Query("DELETE FROM trip_table WHERE countryCode = :code AND type = 'WISHLIST'")
    suspend fun removeWishlist(code: String)
}











// Flow is a stream of data that sends values one by one asynchronously






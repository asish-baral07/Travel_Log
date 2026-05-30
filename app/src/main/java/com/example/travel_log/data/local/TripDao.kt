package com.example.travel_log.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // if same primary key already exists,old data removed, new data is inserted
    suspend fun insertTrip(trip : TripEntity)

    @Query("SELECT * FROM trip_table WHERE type = 'PLANNED'") // fetches data only rows whose type is planned
    fun getPlannedTrips(): Flow<List<TripEntity>>
    // flow means Observe database continuously , if data changes, UI updates automatically

    @Query("SELECT * FROM trip_table WHERE type = 'WISHLIST'")
    fun getWishlistTrips(): Flow<List<TripEntity>> // Continuously observers changes

    @Query("SELECT * FROM trip_table WHERE type = 'VISITED'")
    fun getVisitedTrips(): Flow<List<TripEntity>>  // fetches only visited countries

    @Query("DELETE FROM trip_table WHERE countryCode = :code") //:code -> is a parameter placeholder, Room replaces it with actual function argument value
    suspend fun deleteTrip(code : String) // delete trip using country code

    // Check Item Exists or not in Wishlist
    @Query("SELECT EXISTS(SELECT 1 FROM trip_table WHERE countryCode = :code AND type = 'WISHLIST')")
    fun isWishList(code: String): Flow<Boolean> // returns true or false

    // Delete Query
    @Query("DELETE FROM trip_table WHERE countryCode = :code AND type = 'WISHLIST'")
    suspend fun removeWishlist(code: String)
}











// Flow is a stream of data that sends values one by one asynchronously






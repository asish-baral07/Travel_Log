package com.example.travel_log.ui.trip_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travel_log.data.local.TripEntity
import com.example.travel_log.data.model.TripType
import com.example.travel_log.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripsViewModel @Inject constructor(   // Hilt automatically injects TripRepository
    private val repository: TripRepository
) : ViewModel() {

    val plannedTrips =
        repository.getPlannedTrips() // repository fetches planned trips from Room Database
            .stateIn( // stateIn() converts a Flow -> StateFlow
                viewModelScope, // lifecycle aware, when viewModel destroy ,viewModelScope also destroy
                SharingStarted.WhileSubscribed(), // Starts only when UI observes it, Stops when no one is observing
                emptyList()
            )
    val wishlistTrips =
        repository.getWishlistTrips()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                emptyList()
            )
    val visitedTrips =
        repository.getVisitedTrips()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                emptyList()
            )
    val plannedCount =
        plannedTrips.map { it.size } //{ it.size } -> transforms trip list into count integer
            // it -> means List<TripEntity>, size -> Gets total number of trips
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                0 // count starts from 0
            )
    // Counts Wishlist countries
    val wishlistCount =
        wishlistTrips
            .map { it.size }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                0
            )

    val visitedCount =
        visitedTrips
            .map { it.size }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                0
            )

    // move a country into visited tab
    fun moveToVisited(trip : TripEntity){
        viewModelScope.launch {
            repository.deleteTrip(trip.countryCode)
                repository.insertTrip(trip.copy(type = TripType.VISITED)
                )
        }
    }

    fun moveToPlanned(trip: TripEntity){
        viewModelScope.launch {
            repository.deleteTrip(trip.countryCode)
                repository.insertTrip(trip.copy(type = TripType.PLANNED) // Copy -> Creates a new trip object with updated type
                )
        }
    }
}

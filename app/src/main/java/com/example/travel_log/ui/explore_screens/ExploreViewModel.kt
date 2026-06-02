package com.example.travel_log.ui.explore_screens

//import com.example.travelapp.data.remote.api.CountryApiService
//import com.example.travelapp.data.remote.repository.CountryRepositoryImpl
//import com.example.travelapp.domain.model.Country
//import com.example.travelapp.utils.Constants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travel_log.data.model.Country
import com.example.travel_log.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {
    // private val repository = CountryRepository()
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val state: StateFlow<List<Country>> = _countries

    init {
        getCountries()
    }
    private fun getCountries() {

        viewModelScope.launch {

            _countries.value = repository.getAll()
        }
    }
}
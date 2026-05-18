package com.example.travel_log.data.repository

import android.icu.number.Precision.currency
import com.example.travel_log.data.model.Country
import com.example.travel_log.data.remote.CountryServiceApi
import com.example.travel_log.data.remote.RetrofitInstance.api

class CountryRepository() {
    suspend fun getAll(): List<Country> {

        return api.getCountries().map {
            Country(
                code = it.cca2,
                name = it.name.common,
                capital = it.capital?.firstOrNull() ?: "Unknown",
                region = it.region,
                flags = it.flags.png,
                population = it.population,
                currencies =  it.currencies?.values?.firstOrNull()?.symbol ?: "Unknown",
                languages = it.languages?.values?.firstOrNull() ?: "Unknown"
            )
        }
    }
}


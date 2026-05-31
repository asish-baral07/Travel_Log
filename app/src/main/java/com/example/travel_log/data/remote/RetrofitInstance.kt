/*
package com.example.travel_log.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private  const val BASE_URL = "https://restcountries.com/v3.1/"

    val api: CountryServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryServiceApi::class.java)
    }
}
*/

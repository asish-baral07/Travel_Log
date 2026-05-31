package com.example.travel_log.di

import android.content.Context
import androidx.room.Room
import com.example.travel_log.data.local.TripDao
import com.example.travel_log.data.local.TripDatabase
import com.example.travel_log.data.remote.CountryServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
// Makes dependency available throughout application lifecycle
// SingletonComponent - These objects live as long as app lives
object AppModule {  // Singleton Object
    @Provides
    @Singleton // Creates only ONE instance
    // To create and access database file we use context
    fun provideDatabase(@ApplicationContext context: Context): TripDatabase {
        return Room.databaseBuilder(
            context,
            TripDatabase::class.java,
            "travel_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideTripDao( db: TripDatabase): TripDao {
        return db.tripDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryServiceApi(retrofit: Retrofit): CountryServiceApi {
        return retrofit.create(CountryServiceApi::class.java)

    }
}

// DatabaseModule is used to provide Room Database and DAO objects automatically throughout the app
package com.ae_health.data.remote.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimApi {
    @GET("reverse")
    suspend fun reverseGeocode(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("format") format: String = "json"
    ): NominatimResponse
}

data class NominatimResponse(
    val address: Address?
)

data class Address(
    val road: String?,
    val house_number: String?
)

val nominatimRetrofit = Retrofit.Builder()
    .baseUrl("https://nominatim.openstreetmap.org/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val nominatimApi = nominatimRetrofit.create(NominatimApi::class.java)
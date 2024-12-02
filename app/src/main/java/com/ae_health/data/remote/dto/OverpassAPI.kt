package com.ae_health.data.remote.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OverpassApi {
    @GET("interpreter")
    suspend fun getOrganizations(
        @Query("data") query: String
    ): OverpassResponse
}

data class OverpassResponse(
    val elements: List<OverpassElement>
)

data class OverpassElement(
    val type: String,
    val id: Long,
    val lat: Double?,
    val lon: Double?,
    val tags: Map<String, String>?
)

val overpassRetrofit = Retrofit.Builder()
    .baseUrl("https://overpass-api.de/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val overpassApi = overpassRetrofit.create(OverpassApi::class.java)
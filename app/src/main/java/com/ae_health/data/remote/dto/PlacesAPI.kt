package com.ae_health.data.remote.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DgisApi {
    @GET("items")
    suspend fun getOrganizationRating(
        @Query("q") query: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("radius") radius: Int = 100,
        @Query("fields") fields: String = "items.reviews,items.external_content",
        @Query("key") apiKey: String = "31668a73-5c1e-4e4d-9f22-55661a45ffd9"
    ): DgisResponse
}

data class DgisResponse(
    val result: DgisResult
)

data class DgisResult(
    val items: List<DgisItem>
)

data class DgisItem(
    val id: String?,
    val reviews: DgisRating?,
    val external_content: List<DgisExternalContent>?
)

data class DgisRating(
    val general_rating: Double?
)

data class DgisExternalContent(
    val subtype: String,
    val main_photo_url: String?
)

val dgisRetrofit = Retrofit.Builder()
    .baseUrl("https://catalog.api.2gis.com/3.0/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val dgisApi = dgisRetrofit.create(DgisApi::class.java)
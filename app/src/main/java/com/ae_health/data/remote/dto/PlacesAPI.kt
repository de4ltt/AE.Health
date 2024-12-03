package com.ae_health.data.remote.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val api_Key = "76f66567-9425-424d-b1a4-3b0d7a513c8c"

interface DgisApi {
    @GET("items")
    suspend fun getOrganizationRating(
        @Query("q") query: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("radius") radius: Int = 100,
        @Query("fields") fields: String = "items.reviews,items.external_content",
        @Query("key") apiKey: String = api_Key
    ): DgisResponse

    @GET("items/geocode")
    suspend fun getUserCity(
        @Query("key") apiKey: String = api_Key,
        @Query("type") type: String = "adm_div.city",
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): DgisResponse

    @GET("items")
    suspend fun getOrganizationCoordinates(
        @Query("q") query: String,
        @Query("fields") fields: String = "items.point",
        @Query("key") apiKey: String = api_Key
    ): DgisResponse
}

data class DgisResponse(
    val result: DgisResult = DgisResult()
)

data class DgisResult(
    val items: List<DgisItem> = emptyList()
)

data class DgisItem(
    val id: String?,
    val full_name: String?,
    val address_name: String?,
    val point: DgisPoint?,
    val reviews: DgisRating?,
    val external_content: List<DgisExternalContent>?
)

data class DgisPoint(
    val lat: Double?,
    val lon: Double?
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
package com.ae_health.data.repository.util

import android.util.Log
import com.ae_health.data.remote.dto.DgisResponse
import com.ae_health.data.remote.dto.OverpassResponse
import com.ae_health.data.remote.dto.dgisApi
import com.ae_health.data.remote.dto.overpassApi
import com.ae_health.data.remote.model.Organization

suspend fun fetchOrganizations(
    query: String? = null,
    special: List<String> = emptyList(),
    amenities: List<String> = emptyList(),
    lat: Double,
    lon: Double,
    radius: Int = 1000
): List<Organization> {

    val overpassAmenities = amenities.joinToString("|").ifEmpty { "pharmacy|hospital|clinic|laboratory|doctor|dentist|blood_donation" }

    val overpassQuery =
        "[out:json][timeout:10];nwr[\"healthcare\"~\"^($overpassAmenities)\$\"](around:$radius, $lat, $lon);out body;>;out skel qt;"

    val overpassResponse: OverpassResponse = try {
        overpassApi.getOrganizations(overpassQuery)
    } catch (e: Exception) {
        OverpassResponse(elements = emptyList())
    }

    val organizations = overpassResponse.elements.mapNotNull { element ->
        val name = element.tags?.get("name")
        val type = element.tags?.get("healthcare")
        val phone = element.tags?.get("contact:phone")
        val website = element.tags?.get("contact:website")
        val openingHours = element.tags?.get("opening_hours")
        val description = element.tags?.get("description:ru")
        var n_lat = element.lat
        var n_lon = element.lon

        if (element.tags != null) {

            val ruType = when (type) {
                "hospital" -> "больница"
                "pharmacy" -> "аптеки"
                "clinic" -> "поликлиники"
                "laboratory" -> "лаборатория"
                "doctor" -> "доктор"
                "dentist" -> "стоматолог"
                "blood_donation" -> "переливание крови"
                else -> "медицинские организации"
            }

            var ratingResponse: DgisResponse?

            try {

                ratingResponse = if (element.lat != null && element.lon != null) {
                    dgisApi.getOrganizationRating(
                        lat = element.lat,
                        lon = element.lon,
                        query = ruType
                    )
                } else if (name != null) {
                    val city =
                        dgisApi.getUserCity(lat = lat, lon = lon).result.items.firstOrNull()?.full_name

                    val coordinates =
                        dgisApi.getOrganizationCoordinates(query = "$city $name").result.items.firstOrNull { it.address_name != null }?.point
                    if (coordinates?.lat != null && coordinates.lon != null) {

                        n_lat = coordinates.lat
                        n_lon = coordinates.lon

                        dgisApi.getOrganizationRating(
                            lat = coordinates.lat,
                            lon = coordinates.lon,
                            query = ruType
                        )
                    }
                    else null
                } else null
            } catch (e: Exception) {
                ratingResponse = null
            }

            val id = ratingResponse?.result?.items?.firstOrNull()?.id
            val rating = ratingResponse?.result?.items?.firstOrNull()?.reviews?.general_rating
            val imageUrl =
                ratingResponse?.result?.items?.firstOrNull()?.external_content?.firstOrNull { it.subtype == "common" }?.main_photo_url
            val address = ratingResponse?.result?.items?.firstOrNull()?.address_name

            if (id != null)
                return@mapNotNull Organization(
                    id = id,
                    name = name,
                    type = type,
                    latitude = n_lat,
                    longitude = n_lon,
                    phone = phone,
                    website = website,
                    openingHours = openingHours,
                    rating = rating,
                    address = address,
                    imageUrl = imageUrl,
                    additionalInfo = description
                )
            else return@mapNotNull null

        } else return@mapNotNull null
    }

    return if (special.isNotEmpty()) {
        organizations.filter { org -> special.any { spec -> org.name?.contains(spec) == true } }
    } else organizations
}
package com.ae_health.data.repository

import com.ae_health.data.remote.dto.dgisApi
import com.ae_health.data.remote.dto.nominatimApi
import com.ae_health.data.remote.dto.overpassApi
import com.ae_health.data.remote.model.Organization

suspend fun fetchOrganizations(
    amenities: List<String> = listOf("holpital"),
    lat: Double,
    lon: Double,
    radius: Int = 500
): List<Organization> {

    val overpassAmenities = amenities.joinToString("|")

    val overpassQuery =
        "[out:json];nwr[\"amenity\"~\"^($overpassAmenities)\$\"](around:$radius, $lat, $lon);out body;>;out skel qt;"

    val overpassResponse = overpassApi.getOrganizations(overpassQuery)

    print(overpassResponse.elements.joinToString("\n"))

    val organizations = overpassResponse.elements.mapNotNull { element ->
        val name = element.tags?.get("name")
        val type = element.tags?.get("healthcare")
        val phone = element.tags?.get("contact:phone")
        val website = element.tags?.get("contact:website")
        val openingHours = element.tags?.get("opening_hours")
        val description = element.tags?.get("description:ru")
        val street = element.tags?.get("addr:street")
        val house_number = element.tags?.get("addr:housenumber")

        if (element.tags != null) {

            val addressResponse = if (element.lat != null && element.lon != null) {
                nominatimApi.reverseGeocode(element.lat, element.lon)
            } else null
            val address = addressResponse?.address?.let {
                "${it.road ?: ""} ${it.house_number ?: ""}".trim()
            } ?: if (street != null && house_number != null) "$street, $house_number" else null

            val ruType = when (type) {
                "hospital" -> "больницы"
                "pharmacy" -> "аптеки"
                "clinic" -> "поликлиники"
                else -> "медицинские организации"
            }

            val ratingResponse = if (element.lat != null && element.lon != null) {
                val response = dgisApi.getOrganizationRating(
                    lat = element.lat,
                    lon = element.lon,
                    query = ruType
                )
                response
            } else null

            val id = ratingResponse?.result?.items?.firstOrNull()?.id
            val rating = ratingResponse?.result?.items?.firstOrNull()?.reviews?.general_rating
            val imageUrl =
                ratingResponse?.result?.items?.firstOrNull()?.external_content?.firstOrNull { it.subtype == "view" }?.main_photo_url


            if (id != null)
                return@mapNotNull Organization(
                    id = id,
                    name = name,
                    type = type,
                    latitude = element.lat,
                    longitude = element.lon,
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

    return organizations
}

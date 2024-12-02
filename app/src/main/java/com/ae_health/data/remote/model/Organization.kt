package com.ae_health.data.remote.model

data class Organization(
    val id: String?,
    val name: String?,
    val type: String?,
    val latitude: Double?,
    val longitude: Double?,
    val phone: String?,
    val website: String?,
    val openingHours: String?,
    val rating: Double?,
    val additionalInfo: String?,
    val address: String?,
    val imageUrl: String?
)
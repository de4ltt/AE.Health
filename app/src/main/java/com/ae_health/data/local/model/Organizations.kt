package com.ae_health.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Organizations(
    @PrimaryKey
    val organizationId: Long? = null,
    val name: String? = null,
    val type: String? = null,
    val workSchedule: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val description: String? = null,
    val rating: Double? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val website: String? = null,
    val imageUrl: String? = null
)

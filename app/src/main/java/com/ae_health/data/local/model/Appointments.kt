package com.ae_health.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Organizations::class,
            parentColumns = ["organizationId"],
            childColumns = ["organizationId"]
        )
    ]
)
data class Appointments(
    @PrimaryKey(autoGenerate = true)
    val appointmentId: Int? = null,
    val dateTime: String,
    val organizationId: Long,
    val room: String? = null,
    val specialist: String? = null,
    val comment: String? = null
)
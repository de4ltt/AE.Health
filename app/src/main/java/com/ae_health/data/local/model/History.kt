package com.ae_health.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["organizationId", "date"],
    foreignKeys = [
        ForeignKey(
            entity = Organizations::class,
            parentColumns = ["id"],
            childColumns = ["organizationId"]
        )
    ]
)
data class History(
    val organizationId: Long,
    val date: String
)
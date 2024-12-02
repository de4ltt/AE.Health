package com.ae_health.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Organizations::class,
            parentColumns = ["id"],
            childColumns = ["organizationId"]
        )
    ]
)
data class Favourites(
    @PrimaryKey val organizationId: Long
)

package com.ae_health.presentation.model.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ae_health.R

enum class ScreenDestinations(
    @DrawableRes val bottomIcon: Int? = null,
    @StringRes val titleRes: Int? = null
) {
    HOME(
        bottomIcon = R.drawable.home
    ),
    FAVOURITES(
        bottomIcon = R.drawable.heart,
        titleRes = R.string.favourites
    ),
    HISTORY(
        bottomIcon = R.drawable.clock,
        titleRes = R.string.history
    ),
    SCHEDULE(
        bottomIcon = R.drawable.calendar,
        titleRes = R.string.schedule
    ),
    PROFILE(
        bottomIcon = R.drawable.user_circle,
        titleRes = R.string.profile
    ),
}
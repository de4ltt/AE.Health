package com.ae_health.presentation.ui.screen.organization_info_screen_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ae_health.R
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.util.InfoCategory
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.util.InfoTile
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Green
import com.ae_health.presentation.ui.theme.Orange
import com.ae_health.presentation.ui.theme.Red

@Composable
fun InfoTiles(
    modifier: Modifier = Modifier,
    organization: Organization,
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
    ) {
        organization.address?.let {
            item {
                InfoTile(
                    infoCategory = InfoCategory.ADDRESS,
                    comment = it
                )
            }
        }

        organization.phoneNumber?.let {
            item {
                InfoTile(
                    infoCategory = InfoCategory.PHONE,
                    comment = it
                )
            }
        }

        organization.website?.let {
            item {
                InfoTile(
                    infoCategory = InfoCategory.WEBSITE,
                    comment = it,
                    onClick = {}
                )
            }
        }

        organization.workSchedule?.let {
            item {
                InfoTile(
                    workSchedule = it
                )
            }
        }

        organization.rating?.let {
            item {
                InfoTile(
                    rating = it.rating
                )
            }
        }

        organization.comment?.let {
            item {
                InfoTile(
                    infoCategory = InfoCategory.ADDITIONAL_INFO,
                    comment = it
                )
            }
        }
    }
}

enum class Rating(
    val color: Color,
    @StringRes val title: Int
) {
    BAD(color = Red, title = R.string.bad),
    GENERALLY_BAD(color = Red, title = R.string.generally_bad),
    MIXED(color = Orange, title = R.string.mixed),
    GENERALLY_GOOD(color = Green, title = R.string.generally_good),
    GOOD(color = Green, title = R.string.good)
}

private val Float.rating
    get(): Rating = when (this) {
        in 0f.rangeUntil(1f) -> Rating.BAD
        in 1f.rangeUntil(2f) -> Rating.GENERALLY_BAD
        in 2f.rangeUntil(3f) -> Rating.MIXED
        in 3f.rangeUntil(4f) -> Rating.GENERALLY_GOOD
        else -> Rating.GOOD
    }
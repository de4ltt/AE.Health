package com.ae_health.presentation.ui.screen.organization_info_screen_ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.model.util.WorkSchedule
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.Rating
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.ICON
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON_PADDING
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import java.time.DayOfWeek

enum class InfoCategory(
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int
) {
    ADDRESS(iconRes = R.drawable.location, titleRes = R.string.address),
    PHONE(iconRes = R.drawable.phone, titleRes = R.string.phone_number),
    RATING(iconRes = R.drawable.comments, titleRes = R.string.rating),
    ADDITIONAL_INFO(iconRes = R.drawable.information, titleRes = R.string.information),
    SCHEDULE(iconRes = R.drawable.clock, titleRes = R.string.schedule)
}

@Composable
fun InfoTile(
    modifier: Modifier = Modifier,
    infoCategory: InfoCategory,
    comment: String
) {
    Row(
        modifier = modifier
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
        verticalAlignment = Alignment.CenterVertically
    ) {

        InfoTileIcon(infoCategory = infoCategory)

        InfoTileText(title = infoCategory.titleRes, comment = comment)
    }
}

@Composable
fun InfoTile(
    modifier: Modifier = Modifier,
    workSchedule: WorkSchedule
) {
    Row(
        modifier = modifier
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
        verticalAlignment = Alignment.Top
    ) {

        InfoTileIcon(infoCategory = InfoCategory.SCHEDULE)

        InfoTileText(
            modifier = Modifier.padding(top = SMALL_ICON_PADDING),
            title = InfoCategory.SCHEDULE.titleRes,
            comment = workSchedule.string
        )
    }
}

@Composable
fun InfoTile(
    modifier: Modifier = Modifier,
    rating: Rating
) {
    Row(
        modifier = modifier
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
        verticalAlignment = Alignment.CenterVertically
    ) {

        InfoTileIcon(infoCategory = InfoCategory.RATING)

        InfoTileText(
            title = InfoCategory.RATING.titleRes,
            comment = stringResource(rating.title),
            commentColor = rating.color
        )
    }
}

@Composable
private fun InfoTileIcon(
    modifier: Modifier = Modifier,
    infoCategory: InfoCategory
) {

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(Shapes.ICON_ROUNDED)
            .size(ICON)
            .aspectRatio(1f)
            .background(color = ExtendedTheme.extendedColors.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            modifier = Modifier.padding(SMALL_ICON_PADDING),
            painter = painterResource(id = infoCategory.iconRes),
            contentDescription = null,
            tint = ExtendedTheme.extendedColors.primary
        )
    }
}

@Composable
private fun InfoTileText(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    comment: String,
    commentColor: Color = ExtendedTheme.extendedColors.primaryContainer
) {

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(TEXT_SPACING)
    ) {

        RubikFontBasicText(
            text = stringResource(title),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = TextUnits.BAR_TITLE,
                color = ExtendedTheme.extendedColors.secondary
            )
        )

        RubikFontBasicText(
            text = comment,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = TextUnits.BAR_SUBTITLE,
                color = commentColor
            )
        )
    }
}

private val WorkSchedule.string
    @Composable
    get(): String {
        val holiday = stringResource(R.string.holiday)

        val daysOfWeek = DayOfWeek.entries

        var res = ""

        workDays.forEachIndexed { i, day ->
            res += "${stringResource(daysOfWeek[i].string)} " + (if (day.isActive) {
                "${day.start}-${day.end}"
            } else holiday) + if (i != 6) "\n" else ""
        }

        return res
    }

private val DayOfWeek.string
    get(): Int = when (this) {
        DayOfWeek.MONDAY -> R.string.mon
        DayOfWeek.TUESDAY -> R.string.tue
        DayOfWeek.WEDNESDAY -> R.string.wed
        DayOfWeek.THURSDAY -> R.string.thu
        DayOfWeek.FRIDAY -> R.string.fri
        DayOfWeek.SATURDAY -> R.string.sat
        DayOfWeek.SUNDAY -> R.string.sun
    }
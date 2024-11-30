package com.ae_health.presentation.ui.screen.history_screen_ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.BAR_SUBTITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme
import java.time.LocalDate
import java.time.Month

@Composable
fun DateDisplayText(
    date: LocalDate
) {

    val curYear = LocalDate.now().year
    val todayDate = LocalDate.now()
    val yesterdayDate = LocalDate.now().minusDays(1)

    val today = stringResource(R.string.today)
    val yesterday = stringResource(R.string.yesterday)
    val curDate = "${date.dayOfMonth} ${stringResource(date.month.stringRes)}"
    val curDateWithYear = "$curDate ${date.year}"

    val value = when {
        date == todayDate -> today
        date == yesterdayDate -> yesterday
        date.year == curYear -> curDate
        else -> curDateWithYear
    }

    val color = ExtendedTheme.extendedColors.secondary

    RubikFontBasicText(
        text = value,
        basicMarqueeEnabled = false,
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = BAR_SUBTITLE
        ),
        color = { color }
    )
}

private val Month.stringRes
    @StringRes
    get(): Int = when (this) {
        Month.JANUARY -> R.string.jan
        Month.FEBRUARY -> R.string.feb
        Month.MARCH -> R.string.mar
        Month.APRIL -> R.string.apr
        Month.MAY -> R.string.may
        Month.JUNE -> R.string.jun
        Month.JULY -> R.string.jul
        Month.AUGUST -> R.string.aug
        Month.SEPTEMBER -> R.string.sep
        Month.OCTOBER -> R.string.oct
        Month.NOVEMBER -> R.string.nov
        Month.DECEMBER -> R.string.dec
    }
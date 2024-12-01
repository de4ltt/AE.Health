package com.ae_health.presentation.ui.screen.history_screen_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.BAR_SUBTITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.util.stringRes
import java.time.LocalDate

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
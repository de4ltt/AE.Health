package com.ae_health.presentation.ui.screen.schedule_screen_ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.BAR_TITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme

enum class InfoType(
    @DrawableRes val iconRes: Int? = null,
    @StringRes val titleRes: Int
) {
    ADDRESS(titleRes = R.string.address),
    ROOM(titleRes = R.string.room),
    SPECIALIST(titleRes = R.string.specialist),
    COMMENT(titleRes = R.string.comment)
}

@Composable
fun ScheduleNoteInfoText(
    infoType: InfoType,
    text: String
) {

    val color = ExtendedTheme.extendedColors.secondary

    RubikFontBasicText(
        basicMarqueeEnabled = false,
        color = { color },
        text = buildAnnotatedString {
            withStyle(SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = BAR_TITLE
            )) {
                append(stringResource(infoType.titleRes) + ": ")
            }

            withStyle(SpanStyle(
                fontWeight = FontWeight.Normal,
                fontSize = BAR_TITLE
            )) {
                append(text)
            }
        }
    )
}
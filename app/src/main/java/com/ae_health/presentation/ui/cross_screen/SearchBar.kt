package com.ae_health.presentation.ui.cross_screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ae_health.R
import com.ae_health.presentation.theme.Shapes.ICON_ROUNDED
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.Dimens
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.theme.extendedFonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    textValue: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .clip(ICON_ROUNDED)
            .background(ExtendedTheme.extendedColors.secondaryContainer),
        textStyle = TextStyle(
            fontFamily = extendedFonts,
            fontWeight = FontWeight.Normal,
            fontSize = TextUnits.SEARCH,
            color = ExtendedTheme.extendedColors.secondary
        ),
        value = textValue,
        onValueChange = onValueChange
    ) { textField ->

        Row(
            modifier = Modifier.padding(Dimens.FIELD_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.DEFAULT_SPACING)
        ) {

            val iconTint = ExtendedTheme.extendedColors.primaryContainer

            Icon(
                modifier = Modifier.size(Dimens.SMALL_ICON),
                painter = painterResource(id = R.drawable.search_glass),
                tint = iconTint,
                contentDescription = null,
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                Crossfade(textValue.isEmpty(), label = "") {
                    if (it) {
                        RubikFontBasicText(
                            text = stringResource(id = R.string.search_for_med_org),
                            style = TextStyle(
                                color = ExtendedTheme.extendedColors.primaryContainer,
                                fontSize = TextUnits.SEARCH,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    } else textField()
                }
            }
        }
    }
}
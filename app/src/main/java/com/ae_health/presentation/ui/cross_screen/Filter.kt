package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.filters
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits.FILTER_LARGE
import com.ae_health.presentation.theme.TextUnits.FILTER_SMALL
import com.ae_health.presentation.ui.cross_screen.util.AppButtonText
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON_PADDING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.transport.ui.util.bounceClick

@OptIn(ExperimentalLayoutApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun Filter(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onClick: (Filter?) -> Unit
) {

    SharedTransitionLayout {
        AnimatedContent(
            modifier = Modifier
                .skipToLookaheadSize()
                .wrapContentSize(),
            targetState = state.isSearchActive, label = ""
        ) {
            if (!it)
                LazyRow(
                    modifier = modifier,
                    contentPadding = PaddingValues(start = DEFAULT_SPACING),
                    horizontalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
                ) {
                    item {
                        FilterTileSmall(
                            modifier = Modifier.sharedBounds(
                                animatedVisibilityScope = this@AnimatedContent,
                                sharedContentState = rememberSharedContentState(key = "null")
                            ),
                            title = R.string.all,
                            isActive = state.curFilters.isEmpty(),
                            onClick = { onClick(null) }
                        )
                    }

                    items(filters) {
                        FilterTileSmall(
                            modifier = Modifier.sharedBounds(
                                animatedVisibilityScope = this@AnimatedContent,
                                sharedContentState = rememberSharedContentState(key = "${it.titleRes}")
                            ),
                            title = it.titleRes,
                            isActive = state.curFilters.contains(it),
                            onClick = { onClick(it) }
                        )
                    }
                }
            else {

                Column(
                    modifier = Modifier.skipToLookaheadSize(),
                    verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
                ) {

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = DEFAULT_SPACING,
                                end = DEFAULT_SPACING
                            )
                            .skipToLookaheadSize(),
                        horizontalArrangement = Arrangement.spacedBy(MEDIUM_SPACING),
                        verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
                    ) {

                        FilterTileLarge(
                            modifier = Modifier.sharedBounds(
                                animatedVisibilityScope = this@AnimatedContent,
                                sharedContentState = rememberSharedContentState(key = "null")
                            ),
                            title = R.string.all,
                            isActive = state.curFilters.isEmpty(),
                            onClick = { onClick(null) }
                        )


                        filters.forEach {
                            FilterTileLarge(
                                modifier = Modifier.sharedBounds(
                                    animatedVisibilityScope = this@AnimatedContent,
                                    sharedContentState = rememberSharedContentState(key = "${it.titleRes}")
                                ),
                                title = it.titleRes,
                                isActive = state.curFilters.contains(it),
                                onClick = { onClick(it) }
                            )
                        }
                    }

                    val buttonColor = ExtendedTheme.extendedColors.primary
                    val titleColor = ExtendedTheme.extendedColors.background

                    AppButtonText(
                        modifier = Modifier
                            .padding(horizontal = DEFAULT_SPACING)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .animateEnterExit(),
                        color = { buttonColor },
                        titleColor = { titleColor },
                        title = R.string.start_search,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterTileSmall(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isActive: Boolean = false,
    onClick: () -> Unit
) {

    val barColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.primary else ExtendedTheme.extendedColors.secondaryContainer,
        label = ""
    )

    val textColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.background else ExtendedTheme.extendedColors.primaryContainer,
        label = ""
    )

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(Shapes.ICON_ROUNDED)
            .background(barColor)
            .bounceClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        RubikFontBasicText(
            text = stringResource(id = title),
            modifier = Modifier.padding(SMALL_ICON_PADDING),
            basicMarqueeEnabled = false,
            color = { textColor },
            style = TextStyle(
                fontSize = FILTER_SMALL,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun FilterTileLarge(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    val barColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.primary else ExtendedTheme.extendedColors.secondaryContainer,
        label = ""
    )

    val textColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.background else ExtendedTheme.extendedColors.primaryContainer,
        label = ""
    )

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(Shapes.ICON_ROUNDED)
            .background(barColor)
            .bounceClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        RubikFontBasicText(
            text = stringResource(id = title),
            modifier = Modifier.padding(DEFAULT_SPACING),
            basicMarqueeEnabled = false,
            color = { textColor },
            style = TextStyle(
                fontSize = FILTER_LARGE,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
package com.ae_health.presentation.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.ScreenTitle
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.BottomButtons
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.InfoTiles
import com.ae_health.presentation.ui.screen.organization_info_screen_ui.Map
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import com.ae_health.presentation.ui.theme.Dimens.TOP_PADDING
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun OrganizationInfoScreen(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    organization: Organization? = null,
    onHeartClick: () -> Unit,
    onBack: () -> Unit
) {

    BackHandler { onBack() }

    val context = LocalContext.current

    val onRoute = {
        val uri = Uri.parse("geo:?q=${organization?.lat},${organization?.lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)

        context.startActivity(mapIntent)
    }

    AnimatedVisibility(
        modifier = modifier
            .wrapContentSize()
            .background(ExtendedTheme.extendedColors.background),
        visible = organization != null,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        organization?.let {

            Column(
                modifier = modifier.padding(
                    top = TOP_PADDING,
                )
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
                ) {

                    ScreenTitle(
                        title = it.title.ifEmpty { stringResource(it.type.typeName) }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(DEFAULT_SPACING),
                        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
                    ) {

                        Map(
                            lat = it.lat,
                            lon = it.lon,
                            onClick = onRoute
                        )

                        InfoTiles(
                            modifier = Modifier.fillMaxWidth(),
                            organization = it
                        )
                    }
                }

                BottomButtons(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.08f).padding(horizontal = DEFAULT_SPACING),
                    onHomeClick = onBack,
                    isFavourite = isFavourite,
                    onRouteClick = onRoute,
                    onHeartClick = onHeartClick
                )
            }
        }
    }
}
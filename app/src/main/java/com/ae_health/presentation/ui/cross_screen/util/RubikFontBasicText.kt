package com.ae_health.presentation.ui.cross_screen.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.text.BasicText

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import com.ae_health.presentation.ui.theme.ExtendedTheme

/**
 * Текст со шрифтом Rubik и basicMarquee
 *
 * @param text текст
 * @param modifier модификатор
 * @param color цвет текста
 * @param style стиль текста
 *
 * @author Михаил Гонтарев (KiREHwYE)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RubikFontBasicText(
    text: String,
    modifier: Modifier = Modifier,
    basicMarqueeEnabled: Boolean = true,
    color: ColorProducer? = null,
    style: TextStyle
) {
    BasicText(
        text = text,
        color = color,
        style = style
            .copy(
                fontFamily = ExtendedTheme.extendedFonts,
            ),
        modifier = modifier
            .then(
                if (basicMarqueeEnabled)
                    Modifier.basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately
                    )
                else Modifier
            )
    )
}

/**
 * AnnotatedString со шрифтом Rubik и basicMarquee
 *
 * @param text текст
 * @param modifier модификатор
 * @param color цвет текста
 *
 * @author Михаил Гонтарев (KiREHwYE), Timko Aleksey (de4ltt)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RubikFontBasicText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    basicMarqueeEnabled: Boolean = true,
    color: ColorProducer? = null,
) {
    BasicText(
        text = text,
        color = color,
        modifier = modifier
            .then(
                if (basicMarqueeEnabled)
                    Modifier.basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately
                    )
                else Modifier
            )
    )
}
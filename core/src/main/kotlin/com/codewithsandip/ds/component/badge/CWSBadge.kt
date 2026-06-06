package com.codewithsandip.ds.component.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSTypography
import com.codewithsandip.ds.tokens.CWSColors

/** Semantic intent of a [CWSBadge], driving its color. */
public enum class CWSBadgeStatus { Neutral, Info, Success, Warning, Error }

/** Container/content colors for a [status]. Pure data — no Compose runtime needed. */
internal fun resolveBadgeColors(status: CWSBadgeStatus): Pair<Color, Color> = when (status) {
    CWSBadgeStatus.Neutral -> CWSColors.Neutral500 to CWSColors.White
    CWSBadgeStatus.Info -> CWSColors.Brand500 to CWSColors.White
    CWSBadgeStatus.Success -> CWSColors.Success500 to CWSColors.White
    CWSBadgeStatus.Warning -> CWSColors.Warning500 to CWSColors.White
    CWSBadgeStatus.Error -> CWSColors.Error500 to CWSColors.White
}

/**
 * Small status indicator. With no [text] it renders as a dot; with [text] it renders as a pill
 * label. For numeric counts use [CWSCountBadge].
 *
 * @param modifier Modifier applied to the badge.
 * @param text Optional label; when null the badge is a dot.
 * @param status Semantic intent driving the color.
 * @param contentDescription Accessibility label. Defaults to [text], or "New" for a dot.
 * @sample com.codewithsandip.ds.component.badge.CWSBadgeCountPreview
 */
@Composable
public fun CWSBadge(
    modifier: Modifier = Modifier,
    text: String? = null,
    status: CWSBadgeStatus = CWSBadgeStatus.Error,
    contentDescription: String? = null,
) {
    val typography = LocalCWSTypography.current
    // Touch the color scheme so badges re-resolve if the theme changes brand mid-composition.
    LocalCWSColorScheme.current
    val (container, content) = resolveBadgeColors(status)
    val description = contentDescription ?: text ?: "New"

    if (text == null) {
        Box(
            modifier = modifier
                .size(8.dp)
                .background(container, CircleShape)
                .clearAndSetSemantics { this.contentDescription = description },
        )
    } else {
        Box(
            modifier = modifier
                .defaultMinSize(minWidth = 18.dp, minHeight = 18.dp)
                .background(container, CircleShape)
                .padding(horizontal = 6.dp, vertical = 2.dp)
                .clearAndSetSemantics { this.contentDescription = description },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, style = typography.LabelSmall, color = content)
        }
    }
}

/**
 * Badge that displays a numeric [count], clamping to `"[max]+"` when it exceeds [max].
 *
 * @param count Number to display.
 * @param modifier Modifier applied to the badge.
 * @param max Largest exact number shown before switching to `"[max]+"`.
 * @param status Semantic intent driving the color.
 */
@Composable
public fun CWSCountBadge(
    count: Int,
    modifier: Modifier = Modifier,
    max: Int = 99,
    status: CWSBadgeStatus = CWSBadgeStatus.Error,
) {
    CWSBadge(modifier = modifier, text = formatCount(count, max), status = status)
}

/** Formats [count] as text, using `"[max]+"` once it exceeds [max]. Pure logic. */
internal fun formatCount(count: Int, max: Int): String =
    if (count > max) "$max+" else count.toString()

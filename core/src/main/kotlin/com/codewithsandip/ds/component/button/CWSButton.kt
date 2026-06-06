package com.codewithsandip.ds.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.theme.CWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes
import com.codewithsandip.ds.theme.LocalCWSSpacing
import com.codewithsandip.ds.theme.LocalCWSTypography

/** Visual style of a [CWSButton]. */
public enum class CWSButtonVariant {
    /** Filled, high-emphasis primary action. */
    Primary,

    /** Outlined, medium-emphasis action. */
    Secondary,

    /** Text-only, low-emphasis action. */
    Ghost,

    /** Filled destructive action using the error color. */
    Danger,
}

/** Controls the height, padding, icon size, and text style of a [CWSButton]. */
public enum class CWSButtonSize { Small, Medium, Large }

/** Alpha applied to a disabled button's content (and filled container/border). WCAG-aligned. */
internal const val CWSButtonDisabledAlpha: Float = 0.38f

/**
 * Resolved container/content/border colors for a [variant], before any disabled-state alpha.
 * Pure data — no Compose runtime needed — so it can be unit-tested on the JVM.
 */
internal data class CWSButtonColors(
    val container: Color,
    val content: Color,
    val border: Color,
)

/** Maps a [variant] onto the semantic roles of [scheme]. */
internal fun resolveButtonColors(
    scheme: CWSColorScheme,
    variant: CWSButtonVariant,
): CWSButtonColors = when (variant) {
    CWSButtonVariant.Primary -> CWSButtonColors(
        container = scheme.primary,
        content = scheme.onPrimary,
        border = Color.Transparent,
    )
    CWSButtonVariant.Secondary -> CWSButtonColors(
        container = Color.Transparent,
        content = scheme.primary,
        border = scheme.outline,
    )
    CWSButtonVariant.Ghost -> CWSButtonColors(
        container = Color.Transparent,
        content = scheme.primary,
        border = Color.Transparent,
    )
    CWSButtonVariant.Danger -> CWSButtonColors(
        container = scheme.error,
        content = scheme.onError,
        border = Color.Transparent,
    )
}

private data class CWSButtonDimensions(
    val minHeight: Dp,
    val horizontalPadding: Dp,
    val iconSize: Dp,
    val textStyle: TextStyle,
)

/**
 * Primary button component for the CWS Design System.
 *
 * Colors come from [LocalCWSColorScheme], padding/sizing from [LocalCWSSpacing] and [size], and
 * corners from [LocalCWSShapes]. The touch target is always at least 48×48dp regardless of the
 * visual size.
 *
 * @param text Label displayed inside the button.
 * @param onClick Called when the button is clicked. Ignored while disabled or loading.
 * @param modifier Modifier applied to the button container.
 * @param variant Visual style — Primary, Secondary, Ghost, or Danger.
 * @param size Controls height, padding, and text size.
 * @param enabled Whether the button accepts user interaction.
 * @param loading Shows a progress indicator in place of the label and blocks interaction.
 * @param leadingIcon Optional icon shown before the label.
 * @param trailingIcon Optional icon shown after the label.
 * @param contentDescription Accessibility label. Defaults to [text].
 * @sample com.codewithsandip.ds.component.button.CWSButtonPrimaryPreview
 */
@Composable
public fun CWSButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CWSButtonVariant = CWSButtonVariant.Primary,
    size: CWSButtonSize = CWSButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    contentDescription: String? = null,
) {
    val scheme = LocalCWSColorScheme.current
    val spacing = LocalCWSSpacing.current
    val shapes = LocalCWSShapes.current
    val typography = LocalCWSTypography.current

    val colors = resolveButtonColors(scheme, variant)
    val interactive = enabled && !loading
    val shape = shapes.Small

    val dimensions = when (size) {
        CWSButtonSize.Small -> CWSButtonDimensions(
            minHeight = 36.dp,
            horizontalPadding = spacing.sm + spacing.xs,
            iconSize = 16.dp,
            textStyle = typography.LabelLarge,
        )
        CWSButtonSize.Medium -> CWSButtonDimensions(
            minHeight = 44.dp,
            horizontalPadding = spacing.md,
            iconSize = 18.dp,
            textStyle = typography.LabelLarge,
        )
        CWSButtonSize.Large -> CWSButtonDimensions(
            minHeight = 52.dp,
            horizontalPadding = spacing.lg,
            iconSize = 20.dp,
            textStyle = typography.TitleSmall,
        )
    }

    val contentColor =
        if (enabled) colors.content else colors.content.copy(alpha = CWSButtonDisabledAlpha)
    val containerColor = colors.container.disabledIfNeeded(enabled)
    val borderColor = colors.border.disabledIfNeeded(enabled)

    val label = contentDescription ?: text
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .heightIn(min = dimensions.minHeight)
            .clip(shape)
            .background(containerColor, shape)
            .then(
                if (borderColor != Color.Transparent) Modifier.border(1.dp, borderColor, shape)
                else Modifier,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = interactive,
                onClick = onClick,
            )
            .semantics(mergeDescendants = true) {
                this.role = Role.Button
                this.contentDescription = label
            }
            .padding(horizontal = dimensions.horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(dimensions.iconSize),
                color = contentColor,
                strokeWidth = 2.dp,
            )
        } else {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(dimensions.iconSize),
                )
            }
            Text(text = text, style = dimensions.textStyle, color = contentColor, maxLines = 1)
            trailingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(dimensions.iconSize),
                )
            }
        }
    }
}

/** Transparent stays transparent; an opaque color is dimmed to [CWSButtonDisabledAlpha] when disabled. */
private fun Color.disabledIfNeeded(enabled: Boolean): Color = when {
    this == Color.Transparent -> Color.Transparent
    enabled -> this
    else -> copy(alpha = CWSButtonDisabledAlpha)
}

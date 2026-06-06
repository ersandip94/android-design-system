package com.codewithsandip.ds.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.tokens.CWSElevation
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes
import com.codewithsandip.ds.theme.LocalCWSSpacing

/** Visual style of a [CWSCard]. */
public enum class CWSCardVariant {
    /** Raised surface with a shadow. */
    Elevated,

    /** Flat surface with an outline. */
    Outlined,

    /** Flat surface tinted with the primary container color. */
    Filled,
}

/**
 * Container surface for grouping related content in the CodeWithSandip Design System.
 *
 * Colors come from [LocalCWSColorScheme], corners from [LocalCWSShapes], and inner padding from
 * [LocalCWSSpacing]. Pass [onClick] to make the whole card a single tappable target.
 *
 * @param modifier Modifier applied to the card.
 * @param variant Visual style — Elevated, Outlined, or Filled.
 * @param onClick Optional click handler; when non-null the card becomes clickable.
 * @param enabled Whether a clickable card responds to interaction.
 * @param content Card body, laid out in a [ColumnScope].
 * @sample com.codewithsandip.ds.component.card.CWSCardElevatedPreview
 */
@Composable
public fun CWSCard(
    modifier: Modifier = Modifier,
    variant: CWSCardVariant = CWSCardVariant.Elevated,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scheme = LocalCWSColorScheme.current
    val shapes = LocalCWSShapes.current
    val spacing = LocalCWSSpacing.current

    val shape = shapes.Medium
    val containerColor =
        if (variant == CWSCardVariant.Filled) scheme.primaryContainer else scheme.surface
    val contentColor =
        if (variant == CWSCardVariant.Filled) scheme.onPrimaryContainer else scheme.onSurface
    val elevation =
        if (variant == CWSCardVariant.Elevated) CWSElevation.Level1 else CWSElevation.Level0
    val border =
        if (variant == CWSCardVariant.Outlined) BorderStroke(1.dp, scheme.outline) else null

    val body: @Composable () -> Unit = {
        Column(modifier = Modifier.padding(spacing.md), content = content)
    }

    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            shadowElevation = elevation,
            border = border,
            content = body,
        )
    } else {
        Surface(
            modifier = modifier,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            shadowElevation = elevation,
            border = border,
            content = body,
        )
    }
}

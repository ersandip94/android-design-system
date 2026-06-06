package com.codewithsandip.ds.component.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.theme.LocalCWSShapes

/** Behavior/visual style of a [CWSChip]. */
public enum class CWSChipVariant {
    /** Toggleable chip that reflects a [selected] state. */
    Filter,

    /** Represents a discrete entry the user can remove via the close action. */
    Input,

    /** A dynamically generated suggestion the user can tap. */
    Suggestion,
}

/**
 * Compact chip for filters, entries, and suggestions. Wraps the Material 3 chips, which inherit
 * CWS brand colors through the theme; corners come from [LocalCWSShapes].
 *
 * @param label Text shown in the chip.
 * @param onClick Called when the chip body is tapped.
 * @param modifier Modifier applied to the chip.
 * @param variant Filter, Input, or Suggestion.
 * @param selected Selection state, used by the [CWSChipVariant.Filter] and [CWSChipVariant.Input] variants.
 * @param enabled Whether the chip responds to interaction.
 * @param leadingIcon Optional icon shown before the label.
 * @param onClose Optional remove handler; when non-null an [CWSChipVariant.Input] chip shows a close action.
 * @sample com.codewithsandip.ds.component.chip.CWSChipFilterPreview
 */
@Composable
public fun CWSChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: CWSChipVariant = CWSChipVariant.Filter,
    selected: Boolean = false,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    onClose: (() -> Unit)? = null,
) {
    val shape = LocalCWSShapes.current.Small
    val labelContent: @Composable () -> Unit = { Text(label) }
    val leading: (@Composable () -> Unit)? = leadingIcon?.let {
        { Icon(it, contentDescription = null, modifier = Modifier.size(18.dp)) }
    }

    when (variant) {
        CWSChipVariant.Filter -> FilterChip(
            selected = selected,
            onClick = onClick,
            label = labelContent,
            modifier = modifier,
            enabled = enabled,
            leadingIcon = leading,
            shape = shape,
        )

        CWSChipVariant.Input -> InputChip(
            selected = selected,
            onClick = onClick,
            label = labelContent,
            modifier = modifier,
            enabled = enabled,
            leadingIcon = leading,
            trailingIcon = onClose?.let { close ->
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove $label",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable(enabled = enabled, onClick = close),
                    )
                }
            },
            shape = shape,
        )

        CWSChipVariant.Suggestion -> SuggestionChip(
            onClick = onClick,
            label = labelContent,
            modifier = modifier,
            enabled = enabled,
            icon = leading,
            shape = shape,
        )
    }
}

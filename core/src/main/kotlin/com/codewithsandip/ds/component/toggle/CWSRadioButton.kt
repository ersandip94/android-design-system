package com.codewithsandip.ds.component.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.codewithsandip.ds.theme.LocalCWSSpacing
import com.codewithsandip.ds.theme.LocalCWSTypography

/**
 * Radio button for selecting a single option from a set. Pass a [label] to render a tappable row
 * (with [Role.RadioButton] semantics).
 *
 * @param selected Whether this option is selected.
 * @param onClick Called when the option is chosen; null makes it read-only.
 * @param modifier Modifier applied to the button (or the labeled row).
 * @param enabled Whether the button responds to interaction.
 * @param label Optional text shown beside the button.
 * @sample com.codewithsandip.ds.component.toggle.CWSRadioButtonPreview
 */
@Composable
public fun CWSRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    if (label == null) {
        RadioButton(selected = selected, onClick = onClick, modifier = modifier, enabled = enabled)
        return
    }

    val spacing = LocalCWSSpacing.current
    val typography = LocalCWSTypography.current
    Row(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier.selectable(
                        selected = selected,
                        enabled = enabled,
                        role = Role.RadioButton,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            )
            .minimumInteractiveComponentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        RadioButton(selected = selected, onClick = null, enabled = enabled)
        Text(text = label, style = typography.BodyMedium)
    }
}

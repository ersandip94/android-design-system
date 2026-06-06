package com.codewithsandip.ds.component.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.codewithsandip.ds.theme.LocalCWSSpacing
import com.codewithsandip.ds.theme.LocalCWSTypography

/**
 * Switch for toggling a single setting on or off. Pass a [label] to render a tappable row (with
 * the label leading and the switch trailing) using [Role.Switch] semantics.
 *
 * @param checked Whether the switch is on.
 * @param onCheckedChange Called with the new state; null makes the switch read-only.
 * @param modifier Modifier applied to the switch (or the labeled row).
 * @param enabled Whether the switch responds to interaction.
 * @param label Optional text shown before the switch.
 * @sample com.codewithsandip.ds.component.toggle.CWSSwitchPreview
 */
@Composable
public fun CWSSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    if (label == null) {
        Switch(checked = checked, onCheckedChange = onCheckedChange, modifier = modifier, enabled = enabled)
        return
    }

    val spacing = LocalCWSSpacing.current
    val typography = LocalCWSTypography.current
    Row(
        modifier = modifier
            .then(
                if (onCheckedChange != null) {
                    Modifier.toggleable(
                        value = checked,
                        enabled = enabled,
                        role = Role.Switch,
                        onValueChange = onCheckedChange,
                    )
                } else {
                    Modifier
                },
            )
            .minimumInteractiveComponentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        Text(text = label, style = typography.BodyMedium, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = null, enabled = enabled)
    }
}

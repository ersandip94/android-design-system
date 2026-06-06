package com.codewithsandip.ds.component.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.codewithsandip.ds.theme.LocalCWSSpacing
import com.codewithsandip.ds.theme.LocalCWSTypography

/**
 * Checkbox for binary, independent choices. Pass a [label] to render a tappable row whose whole
 * width toggles the box (with the correct [Role.Checkbox] semantics).
 *
 * @param checked Whether the box is checked.
 * @param onCheckedChange Called with the new state; null makes the checkbox read-only.
 * @param modifier Modifier applied to the checkbox (or the labeled row).
 * @param enabled Whether the checkbox responds to interaction.
 * @param label Optional text shown beside the box.
 * @sample com.codewithsandip.ds.component.toggle.CWSCheckboxPreview
 */
@Composable
public fun CWSCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    if (label == null) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange, modifier = modifier, enabled = enabled)
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
                        role = Role.Checkbox,
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
        Checkbox(checked = checked, onCheckedChange = null, enabled = enabled)
        Text(text = label, style = typography.BodyMedium)
    }
}

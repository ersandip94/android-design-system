package com.codewithsandip.codewithsandip_ds.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codewithsandip.codewithsandip_ds.theme.ThemeMode
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonSize
import com.codewithsandip.ds.component.button.CWSButtonVariant

/**
 * Top-bar control that shows the current [ThemeMode] and cycles to the next one when tapped.
 *
 * @param mode Current theme mode.
 * @param onChange Called with the next mode when tapped.
 */
@Composable
fun ThemeSwitcher(
    mode: ThemeMode,
    onChange: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    CWSButton(
        text = mode.label,
        onClick = { onChange(mode.next()) },
        modifier = modifier,
        variant = CWSButtonVariant.Ghost,
        size = CWSButtonSize.Small,
        contentDescription = "Theme: ${mode.label}. Tap to switch.",
    )
}

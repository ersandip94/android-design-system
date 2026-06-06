package com.codewithsandip.ds.component.toggle

import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Slider for choosing a value from a continuous (or, with [steps], discrete) range. Inherits CWS
 * brand colors through the theme.
 *
 * @param value Current value.
 * @param onValueChange Called continuously as the user drags.
 * @param modifier Modifier applied to the slider.
 * @param enabled Whether the slider responds to interaction.
 * @param valueRange Inclusive range of selectable values.
 * @param steps Number of discrete stops between the range ends; 0 means continuous.
 * @param onValueChangeFinished Called once the user stops dragging.
 * @sample com.codewithsandip.ds.component.toggle.CWSSliderPreview
 */
@Composable
public fun CWSSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
    )
}

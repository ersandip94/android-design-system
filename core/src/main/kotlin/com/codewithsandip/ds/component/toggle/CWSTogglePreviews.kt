package com.codewithsandip.ds.component.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSCheckboxPreview() {
    CWSTheme {
        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CWSCheckbox(checked = true, onCheckedChange = {}, label = "Subscribe to updates")
            CWSCheckbox(checked = false, onCheckedChange = {}, label = "Send me promotions")
            CWSCheckbox(checked = false, onCheckedChange = {}, enabled = false, label = "Disabled option")
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSRadioButtonPreview() {
    CWSTheme {
        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CWSRadioButton(selected = true, onClick = {}, label = "Light")
            CWSRadioButton(selected = false, onClick = {}, label = "Dark")
            CWSRadioButton(selected = false, onClick = {}, label = "System")
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSSwitchPreview() {
    CWSTheme {
        Column(modifier = Modifier.padding(8.dp).width(220.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CWSSwitch(checked = true, onCheckedChange = {}, label = "Wi-Fi")
            CWSSwitch(checked = false, onCheckedChange = {}, label = "Bluetooth")
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSSliderPreview() {
    CWSTheme {
        Column(modifier = Modifier.padding(8.dp).width(220.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            CWSSlider(value = 0.4f, onValueChange = {})
            CWSSlider(value = 2f, onValueChange = {}, valueRange = 0f..4f, steps = 3)
        }
    }
}

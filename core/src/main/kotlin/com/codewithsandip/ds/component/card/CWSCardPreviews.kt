package com.codewithsandip.ds.component.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSCardElevatedPreview() {
    CWSTheme {
        CWSCard(modifier = Modifier.padding(8.dp).width(220.dp), variant = CWSCardVariant.Elevated) {
            Text("Elevated card")
            Text("Raised surface with a shadow.")
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSCardOutlinedPreview() {
    CWSTheme {
        CWSCard(modifier = Modifier.padding(8.dp).width(220.dp), variant = CWSCardVariant.Outlined) {
            Text("Outlined card")
            Text("Flat surface with an outline.")
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSCardFilledPreview() {
    CWSTheme {
        CWSCard(modifier = Modifier.padding(8.dp).width(220.dp), variant = CWSCardVariant.Filled) {
            Text("Filled card")
            Text("Tinted with the primary container.")
        }
    }
}

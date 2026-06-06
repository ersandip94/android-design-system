package com.codewithsandip.ds.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSChipFilterPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CWSChip(label = "All", onClick = {}, variant = CWSChipVariant.Filter, selected = true, leadingIcon = Icons.Default.Check)
            CWSChip(label = "Unread", onClick = {}, variant = CWSChipVariant.Filter, selected = false)
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSChipInputPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CWSChip(label = "sandip@x.com", onClick = {}, variant = CWSChipVariant.Input, onClose = {})
            CWSChip(label = "team", onClick = {}, variant = CWSChipVariant.Input, selected = true, onClose = {})
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSChipSuggestionPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CWSChip(label = "Compose", onClick = {}, variant = CWSChipVariant.Suggestion)
            CWSChip(label = "Kotlin", onClick = {}, variant = CWSChipVariant.Suggestion)
        }
    }
}

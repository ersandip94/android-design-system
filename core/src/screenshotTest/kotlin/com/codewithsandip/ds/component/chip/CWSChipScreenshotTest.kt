package com.codewithsandip.ds.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

class CWSChipScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun chipFilter() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp)) {
                CWSChip("All", onClick = {}, variant = CWSChipVariant.Filter, selected = true, leadingIcon = Icons.Default.Check)
                CWSChip("Unread", onClick = {}, variant = CWSChipVariant.Filter, selected = false)
            }
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun chipInput() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp)) {
                CWSChip("sandip@x.com", onClick = {}, variant = CWSChipVariant.Input, onClose = {})
                CWSChip("team", onClick = {}, variant = CWSChipVariant.Input, selected = true, onClose = {})
            }
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun chipSuggestion() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp)) {
                CWSChip("Compose", onClick = {}, variant = CWSChipVariant.Suggestion)
                CWSChip("Kotlin", onClick = {}, variant = CWSChipVariant.Suggestion)
            }
        }
    }
}

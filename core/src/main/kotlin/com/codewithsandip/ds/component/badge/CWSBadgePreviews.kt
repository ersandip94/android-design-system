package com.codewithsandip.ds.component.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSBadgeDotPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CWSBadge(status = CWSBadgeStatus.Error)
            CWSBadge(status = CWSBadgeStatus.Success)
            CWSBadge(status = CWSBadgeStatus.Warning)
            CWSBadge(status = CWSBadgeStatus.Info)
            CWSBadge(status = CWSBadgeStatus.Neutral)
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSBadgeCountPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CWSCountBadge(count = 3)
            CWSCountBadge(count = 42, status = CWSBadgeStatus.Info)
            CWSCountBadge(count = 128)
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSBadgeStatusPreview() {
    CWSTheme {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CWSBadge(text = "New", status = CWSBadgeStatus.Info)
            CWSBadge(text = "Live", status = CWSBadgeStatus.Success)
            CWSBadge(text = "Beta", status = CWSBadgeStatus.Warning)
        }
    }
}

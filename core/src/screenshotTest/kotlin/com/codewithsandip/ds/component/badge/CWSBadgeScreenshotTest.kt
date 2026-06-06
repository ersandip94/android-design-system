package com.codewithsandip.ds.component.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

class CWSBadgeScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun badgeDots() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp), Alignment.CenterVertically) {
                CWSBadge(status = CWSBadgeStatus.Error)
                CWSBadge(status = CWSBadgeStatus.Success)
                CWSBadge(status = CWSBadgeStatus.Warning)
                CWSBadge(status = CWSBadgeStatus.Info)
                CWSBadge(status = CWSBadgeStatus.Neutral)
            }
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun badgeCounts() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp), Alignment.CenterVertically) {
                CWSCountBadge(count = 3)
                CWSCountBadge(count = 42, status = CWSBadgeStatus.Info)
                CWSCountBadge(count = 128)
            }
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun badgeStatusLabels() {
        CWSTheme {
            Row(Modifier.padding(8.dp), Arrangement.spacedBy(8.dp), Alignment.CenterVertically) {
                CWSBadge(text = "New", status = CWSBadgeStatus.Info)
                CWSBadge(text = "Live", status = CWSBadgeStatus.Success)
                CWSBadge(text = "Beta", status = CWSBadgeStatus.Warning)
            }
        }
    }
}

package com.codewithsandip.ds.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

class CWSNavigationBarScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun navigationBar() {
        CWSTheme {
            CWSNavigationBar(
                items = listOf(
                    CWSNavigationItem("Home", Icons.Default.Home),
                    CWSNavigationItem("Favorites", Icons.Default.Favorite, badgeCount = 5),
                    CWSNavigationItem("Settings", Icons.Default.Settings),
                ),
                selectedIndex = 0,
                onSelect = {},
            )
        }
    }
}

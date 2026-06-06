package com.codewithsandip.ds.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSNavigationBarPreview() {
    CWSTheme {
        CWSNavigationBar(
            items = listOf(
                CWSNavigationItem(label = "Home", icon = Icons.Default.Home),
                CWSNavigationItem(label = "Favorites", icon = Icons.Default.Favorite, badgeCount = 5),
                CWSNavigationItem(label = "Settings", icon = Icons.Default.Settings),
            ),
            selectedIndex = 0,
            onSelect = {},
        )
    }
}

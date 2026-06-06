package com.codewithsandip.ds.component.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSTopBarPreview() {
    CWSTheme {
        CWSTopBar(
            title = "Components",
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationClick = {},
            navigationContentDescription = "Back",
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
            },
        )
    }
}

package com.codewithsandip.ds.component.navigation

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.codewithsandip.ds.component.badge.CWSCountBadge

/**
 * A single destination in a [CWSNavigationBar].
 *
 * @param label Text shown under the icon.
 * @param icon Icon representing the destination.
 * @param badgeCount Optional unread/notification count shown as a badge over the icon.
 */
@Immutable
public data class CWSNavigationItem(
    public val label: String,
    public val icon: ImageVector,
    public val badgeCount: Int? = null,
)

/**
 * Bottom navigation bar for switching between top-level destinations.
 *
 * @param items Destinations to show.
 * @param selectedIndex Index of the currently selected destination.
 * @param onSelect Called with the index of a tapped destination.
 * @param modifier Modifier applied to the bar.
 * @sample com.codewithsandip.ds.component.navigation.CWSNavigationBarPreview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CWSNavigationBar(
    items: List<CWSNavigationItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onSelect(index) },
                icon = {
                    if (item.badgeCount != null) {
                        BadgedBox(badge = { CWSCountBadge(count = item.badgeCount) }) {
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        }
                    } else {
                        Icon(imageVector = item.icon, contentDescription = item.label)
                    }
                },
                label = { Text(item.label) },
            )
        }
    }
}

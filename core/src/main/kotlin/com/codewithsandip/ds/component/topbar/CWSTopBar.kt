package com.codewithsandip.ds.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSTypography

/**
 * Top app bar showing the screen [title], an optional navigation icon, and trailing [actions].
 * Colors come from [LocalCWSColorScheme].
 *
 * @param title Screen title.
 * @param modifier Modifier applied to the bar.
 * @param navigationIcon Optional leading icon (e.g. back or menu).
 * @param onNavigationClick Click handler for [navigationIcon]; required for it to be tappable.
 * @param navigationContentDescription Accessibility label for the navigation icon.
 * @param actions Trailing action items, laid out in a [RowScope].
 * @sample com.codewithsandip.ds.component.topbar.CWSTopBarPreview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CWSTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    navigationContentDescription: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val scheme = LocalCWSColorScheme.current
    val typography = LocalCWSTypography.current

    TopAppBar(
        title = { Text(text = title, style = typography.TitleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        modifier = modifier,
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = { onNavigationClick?.invoke() }, enabled = onNavigationClick != null) {
                    Icon(imageVector = navigationIcon, contentDescription = navigationContentDescription)
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = scheme.surface,
            titleContentColor = scheme.onSurface,
            navigationIconContentColor = scheme.onSurface,
            actionIconContentColor = scheme.onSurface,
        ),
    )
}

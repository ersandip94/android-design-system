package com.codewithsandip.ds.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes
import com.codewithsandip.ds.theme.LocalCWSSpacing

/**
 * Modal bottom sheet that slides up from the bottom edge for contextual actions or content.
 * Colors and corners come from the theme; the sheet content is laid out in a [ColumnScope].
 *
 * @param onDismissRequest Called when the sheet is dismissed (scrim tap, drag down, or back).
 * @param modifier Modifier applied to the sheet.
 * @param skipPartiallyExpanded Whether the sheet skips the half-expanded state and opens fully.
 * @param content Sheet body.
 * @sample com.codewithsandip.ds.component.bottomsheet.CWSBottomSheetContentPreview
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CWSBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scheme = LocalCWSColorScheme.current
    val spacing = LocalCWSSpacing.current
    val shapes = LocalCWSShapes.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = shapes.Large,
        containerColor = scheme.surface,
        contentColor = scheme.onSurface,
        scrimColor = scheme.scrim.copy(alpha = 0.32f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.md, vertical = spacing.sm),
            content = content,
        )
    }
}

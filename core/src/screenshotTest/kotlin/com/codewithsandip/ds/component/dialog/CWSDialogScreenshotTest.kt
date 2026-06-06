package com.codewithsandip.ds.component.dialog

import androidx.compose.runtime.Composable
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

class CWSDialogScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun dialogDefault() {
        CWSTheme {
            CWSDialog(
                onDismissRequest = {},
                title = "Save changes?",
                text = "Your edits will be lost if you don't save them.",
                confirmLabel = "Save",
                onConfirm = {},
            )
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun dialogDestructive() {
        CWSTheme {
            CWSDialog(
                onDismissRequest = {},
                title = "Delete project?",
                text = "This permanently removes the project and all its data.",
                confirmLabel = "Delete",
                onConfirm = {},
                variant = CWSDialogVariant.Destructive,
            )
        }
    }
}

package com.codewithsandip.ds.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes

// A modal sheet can't render in the screenshot environment, so this captures the sheet's content
// on the same themed surface the sheet would use.
class CWSBottomSheetScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun bottomSheetContent() {
        CWSTheme {
            Surface(
                color = LocalCWSColorScheme.current.surface,
                contentColor = LocalCWSColorScheme.current.onSurface,
                shape = LocalCWSShapes.current.Large,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Share to")
                    Text("Choose where to send this component.")
                    CWSButton(text = "Copy link", onClick = {}, variant = CWSButtonVariant.Secondary)
                    CWSButton(text = "Done", onClick = {})
                }
            }
        }
    }
}

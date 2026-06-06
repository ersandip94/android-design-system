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
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes

// A modal sheet can't render inside @Preview, so this previews the sheet's content on the same
// themed surface the sheet would use.
@LightDarkPreview
@Composable
private fun CWSBottomSheetContentPreview() {
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

package com.codewithsandip.ds.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSButtonPrimaryPreview() {
    CWSTheme {
        CWSButton(text = "Primary", onClick = {}, variant = CWSButtonVariant.Primary)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonSecondaryPreview() {
    CWSTheme {
        CWSButton(text = "Secondary", onClick = {}, variant = CWSButtonVariant.Secondary)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonGhostPreview() {
    CWSTheme {
        CWSButton(text = "Ghost", onClick = {}, variant = CWSButtonVariant.Ghost)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonDangerPreview() {
    CWSTheme {
        CWSButton(text = "Delete", onClick = {}, variant = CWSButtonVariant.Danger)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonSizesPreview() {
    CWSTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CWSButton(text = "Small", onClick = {}, size = CWSButtonSize.Small)
            CWSButton(text = "Medium", onClick = {}, size = CWSButtonSize.Medium)
            CWSButton(text = "Large", onClick = {}, size = CWSButtonSize.Large)
        }
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonLoadingPreview() {
    CWSTheme {
        CWSButton(text = "Submit", onClick = {}, loading = true)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonDisabledPreview() {
    CWSTheme {
        CWSButton(text = "Disabled", onClick = {}, enabled = false)
    }
}

@LightDarkPreview
@Composable
private fun CWSButtonWithIconsPreview() {
    CWSTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CWSButton(text = "Add item", onClick = {}, leadingIcon = Icons.Default.Add)
            CWSButton(text = "Next", onClick = {}, trailingIcon = Icons.AutoMirrored.Filled.ArrowForward)
        }
    }
}

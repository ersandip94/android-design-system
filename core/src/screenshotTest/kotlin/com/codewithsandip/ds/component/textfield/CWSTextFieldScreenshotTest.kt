package com.codewithsandip.ds.component.textfield

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

class CWSTextFieldScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun textfieldEmpty() {
        CWSTheme {
            CWSTextField("", onValueChange = {}, modifier = Modifier.padding(8.dp), label = "Email", placeholder = "you@example.com")
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun textfieldFilled() {
        CWSTheme {
            CWSTextField("sandip@codewithsandip.com", onValueChange = {}, modifier = Modifier.padding(8.dp), label = "Email", helperText = "We'll never share your email.")
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun textfieldError() {
        CWSTheme {
            CWSTextField("not-an-email", onValueChange = {}, modifier = Modifier.padding(8.dp), label = "Email", isError = true, errorText = "Enter a valid email address.")
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun textfieldDisabled() {
        CWSTheme {
            CWSTextField("Read only", onValueChange = {}, modifier = Modifier.padding(8.dp), label = "Username", enabled = false)
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun textfieldWithIcons() {
        CWSTheme {
            CWSTextField("", onValueChange = {}, modifier = Modifier.padding(8.dp), label = "Search", placeholder = "Search components", leadingIcon = Icons.Default.Search, trailingIcon = Icons.Default.Email)
        }
    }
}

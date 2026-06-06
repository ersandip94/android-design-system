package com.codewithsandip.ds.component.textfield

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

@LightDarkPreview
@Composable
private fun CWSTextFieldEmptyPreview() {
    CWSTheme {
        CWSTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(8.dp),
            label = "Email",
            placeholder = "you@example.com",
        )
    }
}

@LightDarkPreview
@Composable
private fun CWSTextFieldFilledPreview() {
    CWSTheme {
        CWSTextField(
            value = "sandip@codewithsandip.com",
            onValueChange = {},
            modifier = Modifier.padding(8.dp),
            label = "Email",
            helperText = "We'll never share your email.",
        )
    }
}

@LightDarkPreview
@Composable
private fun CWSTextFieldErrorPreview() {
    CWSTheme {
        CWSTextField(
            value = "not-an-email",
            onValueChange = {},
            modifier = Modifier.padding(8.dp),
            label = "Email",
            isError = true,
            errorText = "Enter a valid email address.",
        )
    }
}

@LightDarkPreview
@Composable
private fun CWSTextFieldDisabledPreview() {
    CWSTheme {
        CWSTextField(
            value = "Read only",
            onValueChange = {},
            modifier = Modifier.padding(8.dp),
            label = "Username",
            enabled = false,
        )
    }
}

@LightDarkPreview
@Composable
private fun CWSTextFieldWithIconsPreview() {
    CWSTheme {
        CWSTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(8.dp),
            label = "Search",
            placeholder = "Search components",
            leadingIcon = Icons.Default.Search,
            trailingIcon = Icons.Default.Email,
        )
    }
}

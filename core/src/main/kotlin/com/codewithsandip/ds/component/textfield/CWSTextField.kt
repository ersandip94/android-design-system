package com.codewithsandip.ds.component.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes
import com.codewithsandip.ds.theme.LocalCWSTypography

/**
 * Single- or multi-line text input for the CWS Design System.
 *
 * Renders an outlined field themed from [LocalCWSColorScheme], [LocalCWSShapes], and
 * [LocalCWSTypography]. When [isError] is set, [errorText] replaces [helperText] in the error
 * color; otherwise [helperText] is shown.
 *
 * @param value Current text.
 * @param onValueChange Called when the text changes.
 * @param modifier Modifier applied to the field.
 * @param label Floating label; also used as the accessibility name.
 * @param placeholder Hint shown while the field is empty.
 * @param helperText Supporting text shown below the field when not in an error state.
 * @param errorText Supporting text shown below the field when [isError] is true (takes precedence).
 * @param isError Whether the field is in an error state.
 * @param enabled Whether the field accepts input.
 * @param readOnly Whether the field shows but does not accept edits.
 * @param leadingIcon Optional icon shown before the text.
 * @param trailingIcon Optional icon shown after the text.
 * @param visualTransformation Transforms the visual representation (e.g. password masking).
 * @param keyboardOptions Software-keyboard configuration.
 * @param keyboardActions Keyboard action callbacks.
 * @param singleLine Whether the field is constrained to a single line.
 * @param maxLines Maximum lines when [singleLine] is false.
 * @sample com.codewithsandip.ds.component.textfield.CWSTextFieldFilledPreview
 */
@Composable
public fun CWSTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    helperText: String? = null,
    errorText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
) {
    val scheme = LocalCWSColorScheme.current
    val shapes = LocalCWSShapes.current
    val typography = LocalCWSTypography.current

    val supportingText = resolveSupportingText(isError, errorText, helperText)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else maxLines,
        textStyle = typography.BodyLarge,
        shape = shapes.Small,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
        trailingIcon = trailingIcon?.let { { Icon(it, contentDescription = null) } },
        supportingText = supportingText?.let { { Text(it, style = typography.BodySmall) } },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = scheme.primary,
            unfocusedBorderColor = scheme.outline,
            disabledBorderColor = scheme.outlineVariant,
            errorBorderColor = scheme.error,
            focusedLabelColor = scheme.primary,
            unfocusedLabelColor = scheme.onSurface,
            errorLabelColor = scheme.error,
            cursorColor = scheme.primary,
            errorCursorColor = scheme.error,
            focusedTextColor = scheme.onSurface,
            unfocusedTextColor = scheme.onSurface,
            errorSupportingTextColor = scheme.error,
            focusedLeadingIconColor = scheme.primary,
            unfocusedLeadingIconColor = scheme.onSurface,
        ),
    )
}

/**
 * Picks which supporting line to show below the field: [errorText] in an error state, otherwise
 * [helperText]. Error text takes precedence. Pure logic — no Compose runtime needed.
 */
internal fun resolveSupportingText(
    isError: Boolean,
    errorText: String?,
    helperText: String?,
): String? = when {
    isError -> errorText
    else -> helperText
}

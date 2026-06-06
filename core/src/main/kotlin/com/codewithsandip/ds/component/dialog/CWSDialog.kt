package com.codewithsandip.ds.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonSize
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.theme.LocalCWSColorScheme
import com.codewithsandip.ds.theme.LocalCWSShapes

/** Visual intent of a [CWSDialog], which styles the confirm action. */
public enum class CWSDialogVariant {
    /** Standard confirmation; confirm button is the primary action. */
    Default,

    /** Irreversible/destructive action; confirm button uses the danger style. */
    Destructive,
}

/**
 * Modal dialog for confirmations and alerts, built on the design system buttons.
 *
 * @param onDismissRequest Called when the user dismisses the dialog (scrim tap or back).
 * @param title Dialog title.
 * @param confirmLabel Label for the confirm button.
 * @param onConfirm Called when the confirm button is tapped.
 * @param modifier Modifier applied to the dialog.
 * @param text Optional supporting body text.
 * @param dismissLabel Label for the dismiss button; null hides it.
 * @param onDismiss Called when the dismiss button is tapped; defaults to [onDismissRequest].
 * @param variant Default or Destructive styling for the confirm action.
 * @sample com.codewithsandip.ds.component.dialog.CWSDialogPreview
 */
@Composable
public fun CWSDialog(
    onDismissRequest: () -> Unit,
    title: String,
    confirmLabel: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    dismissLabel: String? = "Cancel",
    onDismiss: (() -> Unit)? = null,
    variant: CWSDialogVariant = CWSDialogVariant.Default,
) {
    val scheme = LocalCWSColorScheme.current
    val confirmVariant =
        if (variant == CWSDialogVariant.Destructive) CWSButtonVariant.Danger else CWSButtonVariant.Primary

    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        shape = LocalCWSShapes.current.Large,
        containerColor = scheme.surface,
        titleContentColor = scheme.onSurface,
        textContentColor = scheme.onSurface,
        title = { Text(text = title) },
        text = text?.let { { Text(text = it) } },
        confirmButton = {
            CWSButton(text = confirmLabel, onClick = onConfirm, variant = confirmVariant, size = CWSButtonSize.Small)
        },
        dismissButton = dismissLabel?.let { label ->
            {
                CWSButton(
                    text = label,
                    onClick = { (onDismiss ?: onDismissRequest)() },
                    variant = CWSButtonVariant.Ghost,
                    size = CWSButtonSize.Small,
                )
            }
        },
    )
}

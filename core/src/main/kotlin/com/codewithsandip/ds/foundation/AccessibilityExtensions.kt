package com.codewithsandip.ds.foundation

import androidx.compose.foundation.clickable
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import kotlin.math.max
import kotlin.math.min

/**
 * Accessible click modifier: enforces a minimum 48×48dp touch target, tags the node with a
 * semantic [role], and exposes an [onClickLabel] for screen readers.
 *
 * Prefer this over a bare `Modifier.clickable` for any custom interactive surface in the design
 * system.
 *
 * @param onClick Called when the element is clicked.
 * @param enabled Whether the element responds to interaction.
 * @param role Accessibility role announced to assistive tech. Defaults to [Role.Button].
 * @param onClickLabel Verb describing the action (e.g. "open"), read after the role.
 */
public fun Modifier.cwsClickable(
    enabled: Boolean = true,
    role: Role = Role.Button,
    onClickLabel: String? = null,
    onClick: () -> Unit,
): Modifier = this
    .minimumInteractiveComponentSize()
    .clickable(enabled = enabled, onClickLabel = onClickLabel, role = role, onClick = onClick)

/**
 * Applies the common accessibility semantics in one place. Each argument is only set when
 * non-null, so callers opt into exactly the properties they need.
 *
 * @param contentDescription Label read by screen readers.
 * @param role Accessibility role.
 * @param stateDescription Description of the current state (e.g. "selected", "expanded").
 */
public fun Modifier.cwsSemantics(
    contentDescription: String? = null,
    role: Role? = null,
    stateDescription: String? = null,
): Modifier = this.semantics {
    contentDescription?.let { this.contentDescription = it }
    role?.let { this.role = it }
    stateDescription?.let { this.stateDescription = it }
}

/**
 * WCAG contrast ratio between this color and [background], from 1.0 (identical) to 21.0
 * (black on white). Order-independent. Both colors are treated as opaque sRGB.
 */
public fun Color.contrastRatio(background: Color): Float {
    val foreground = luminance()
    val backdrop = background.luminance()
    val lighter = max(foreground, backdrop)
    val darker = min(foreground, backdrop)
    return (lighter + 0.05f) / (darker + 0.05f)
}

/**
 * Whether this color meets a WCAG contrast [ratio] against [background]. Defaults to 4.5:1, the
 * AA threshold for normal-size text. Use 3.0f for large text or UI components.
 */
public fun Color.meetsContrastRatio(background: Color, ratio: Float = 4.5f): Boolean =
    contrastRatio(background) >= ratio

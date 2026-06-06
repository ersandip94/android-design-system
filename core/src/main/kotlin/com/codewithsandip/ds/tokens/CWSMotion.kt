package com.codewithsandip.ds.tokens

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing

/**
 * Motion tokens: animation [Duration]s (milliseconds) and standard [Easing] curves.
 * Honor `LocalReduceMotion` before animating — see the accessibility foundation.
 */
public object CWSMotion {
    /** Animation durations in milliseconds. */
    public object Duration {
        public const val Short: Int = 150
        public const val Medium: Int = 300
        public const val Long: Int = 500
    }

    /** Standard (productive) easing — most enter/exit transitions. */
    public val Standard: Easing = FastOutSlowInEasing

    /** Elements entering the screen / expanding. */
    public val Decelerate: Easing = LinearOutSlowInEasing

    /** Elements leaving the screen / collapsing. */
    public val Accelerate: Easing = FastOutLinearInEasing

    /** Constant-speed motion (e.g. loading indicators). */
    public val Linear: Easing = LinearEasing

    /** Expressive, high-emphasis transitions. */
    public val Emphasized: Easing = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
}

package com.codewithsandip.ds.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * Corner-radius scale. [Full] is a 50% radius — a pill/circle depending on the bounds.
 */
public object CWSShape {
    public val None: RoundedCornerShape = RoundedCornerShape(0.dp)
    public val ExtraSmall: RoundedCornerShape = RoundedCornerShape(4.dp)
    public val Small: RoundedCornerShape = RoundedCornerShape(8.dp)
    public val Medium: RoundedCornerShape = RoundedCornerShape(12.dp)
    public val Large: RoundedCornerShape = RoundedCornerShape(16.dp)
    public val ExtraLarge: RoundedCornerShape = RoundedCornerShape(24.dp)
    public val Full: RoundedCornerShape = RoundedCornerShape(percent = 50)
}

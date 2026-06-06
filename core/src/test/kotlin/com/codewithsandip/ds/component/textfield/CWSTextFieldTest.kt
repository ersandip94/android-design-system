package com.codewithsandip.ds.component.textfield

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CWSTextFieldTest {

    @Test
    fun errorState_showsErrorText() {
        assertEquals("Required", resolveSupportingText(isError = true, errorText = "Required", helperText = null))
    }

    @Test
    fun helperText_shownWhenNoError() {
        assertEquals("Enter your email", resolveSupportingText(isError = false, errorText = null, helperText = "Enter your email"))
    }

    @Test
    fun errorText_takesPrecedenceOverHelper() {
        assertEquals("Required", resolveSupportingText(isError = true, errorText = "Required", helperText = "Enter your email"))
    }

    @Test
    fun noSupportingText_whenNoneProvided() {
        assertNull(resolveSupportingText(isError = false, errorText = null, helperText = null))
    }

    @Test
    fun errorState_withoutErrorText_showsNothing() {
        assertNull(resolveSupportingText(isError = true, errorText = null, helperText = "ignored helper"))
    }
}

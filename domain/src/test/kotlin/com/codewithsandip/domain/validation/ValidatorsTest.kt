package com.codewithsandip.domain.validation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorsTest {

    private val validateEmail = ValidateEmail()
    private val validatePassword = ValidatePassword()

    @Test
    fun email_blank_isInvalid() {
        val result = validateEmail("   ")
        assertFalse(result.isValid)
        assertEquals("Email is required", result.errorOrNull)
    }

    @Test
    fun email_withoutAtOrDot_isInvalid() {
        assertFalse(validateEmail("notanemail").isValid)
        assertFalse(validateEmail("missing@dot").isValid)
        assertFalse(validateEmail("@nolocal.com").isValid)
    }

    @Test
    fun email_withWhitespace_isInvalid() {
        assertFalse(validateEmail("a b@c.com").isValid)
    }

    @Test
    fun email_wellFormed_isValid() {
        assertTrue(validateEmail("sandip@codewithsandip.com").isValid)
        assertTrue(validateEmail("  sandip@codewithsandip.com  ").isValid)
    }

    @Test
    fun password_blank_isInvalid() {
        assertEquals("Password is required", validatePassword("").errorOrNull)
    }

    @Test
    fun password_tooShort_isInvalid() {
        val result = validatePassword("abc123")
        assertFalse(result.isValid)
        assertEquals("Password must be at least 8 characters", result.errorOrNull)
    }

    @Test
    fun password_longEnough_isValid() {
        assertTrue(validatePassword("password123").isValid)
    }

    @Test
    fun password_respectsCustomMinLength() {
        val strict = ValidatePassword(minLength = 12)
        assertFalse(strict("password123").isValid)
        assertTrue(strict("password123456").isValid)
    }
}

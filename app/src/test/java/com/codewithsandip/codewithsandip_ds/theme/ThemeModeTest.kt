package com.codewithsandip.codewithsandip_ds.theme

import org.junit.Assert.assertEquals
import org.junit.Test

class ThemeModeTest {

    @Test
    fun next_cyclesLightToDarkToSystem() {
        assertEquals(ThemeMode.Dark, ThemeMode.Light.next())
        assertEquals(ThemeMode.System, ThemeMode.Dark.next())
        assertEquals(ThemeMode.Light, ThemeMode.System.next())
    }

    @Test
    fun labels_areHumanReadable() {
        assertEquals("Light", ThemeMode.Light.label)
        assertEquals("Dark", ThemeMode.Dark.label)
        assertEquals("System", ThemeMode.System.label)
    }
}

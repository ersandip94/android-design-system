package com.codewithsandip.codewithsandip_ds.theme

/** User-selectable theme preference for the catalog app. */
enum class ThemeMode(val label: String) {
    Light("Light"),
    Dark("Dark"),
    System("System");

    /** The next mode in a Light → Dark → System → Light cycle. */
    fun next(): ThemeMode = entries[(ordinal + 1) % entries.size]
}

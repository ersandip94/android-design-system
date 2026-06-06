# Dark mode

By default `CWSTheme` follows the system setting:

```kotlin
CWSTheme {           // light or dark, automatically
    content()
}
```

Under the hood:

```kotlin
colorScheme = if (isSystemInDarkTheme()) cwsDarkColorScheme() else cwsLightColorScheme()
```

## Forcing a scheme

Drive it from your own state (e.g. a Light / Dark / System preference):

```kotlin
val scheme = when (themeMode) {
    ThemeMode.Light  -> cwsLightColorScheme()
    ThemeMode.Dark   -> cwsDarkColorScheme()
    ThemeMode.System -> if (isSystemInDarkTheme()) cwsDarkColorScheme() else cwsLightColorScheme()
}
CWSTheme(colorScheme = scheme) { content() }
```

The dark scheme uses `Brand300` as the primary color for better contrast on dark surfaces, with
darkened surfaces/backgrounds and lightened error colors.

!!! note
    Every component ships **light and dark** golden screenshots, so dark mode is verified, not
    an afterthought.

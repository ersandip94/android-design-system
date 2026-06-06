# Custom brand

Re-skin the whole design system by passing a custom brand color — no component changes needed.

```kotlin
CWSTheme(
    colorScheme = cwsLightColorScheme(primaryBrand = Color(0xFF6750A4)),
) {
    content()
}
```

`primaryBrand` becomes the scheme's `primary`, and every component that reads `primary` (buttons,
chips, selected states, focus rings, …) updates automatically.

## Other tokens

Beyond colors, the theme also exposes typography, shapes, and spacing — override any of them:

```kotlin
CWSTheme(
    colorScheme = cwsLightColorScheme(primaryBrand = Color(0xFF6750A4)),
    shapes = CWSShape,        // or your own
    spacing = CWSSpacing,
) {
    content()
}
```

!!! tip
    Keep your custom brand color accessible — the system provides
    `Color.meetsContrastRatio(background, ratio = 4.5f)` to check WCAG contrast in tests.

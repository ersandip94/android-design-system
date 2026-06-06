# CWS Design System (`:core`)

A Jetpack Compose component library: design tokens, theming (light / dark / custom brand), and a
curated set of accessible components — all prefixed `CWS`.

- **Artifact:** `com.codewithsandip:ds-core`
- **Brand color:** `#0C3A25` · **Min SDK:** 24 · 100% Kotlin + Compose
- **📖 Documentation:** **<https://ersandip94.github.io/android-design-system/>**

```kotlin
dependencies {
    implementation("com.codewithsandip:ds-core:<version>")
}
```

---

## Quick start

Wrap your UI in `CWSTheme`, then use any `CWS*` component. The theme mirrors its values into
`MaterialTheme`, so Material 3 components inside stay on-brand too.

```kotlin
CWSTheme {
    CWSButton(text = "Get started", onClick = { /* … */ })
}
```

---

## Theming

`CWSTheme` provides the CWS color scheme, typography, shapes, and spacing via CompositionLocals.

```kotlin
// System-driven light/dark (default)
CWSTheme { content() }

// Force a scheme
CWSTheme(colorScheme = cwsDarkColorScheme()) { content() }

// Re-skin with a custom brand color — no component changes needed
CWSTheme(colorScheme = cwsLightColorScheme(primaryBrand = Color(0xFF6750A4))) { content() }
```

Access values anywhere: `MaterialTheme.cws.colorScheme.primary`, `MaterialTheme.cws.spacing.md`.

### Tokens

| Token | Examples |
|---|---|
| `CWSColors` | `Brand900` (#0C3A25) … `Brand50`, `Neutral*`, `Error/Warning/Success*` |
| `CWSSpacing` | `none, xxs(2), xs(4), sm(8), md(16), lg(24), xl(32), xxl(48), xxxl(64)` |
| `CWSTypography` | `DisplayLarge … BodyMedium … LabelSmall` (Material 3 type scale) |
| `CWSShape` | `None, ExtraSmall(4), Small(8), Medium(12), Large(16), ExtraLarge(24), Full` |
| `CWSElevation` | `Level0 … Level5` |
| `CWSMotion` | `Duration` (Short/Medium/Long) + easing |

---

## Components

Full component docs — with **light/dark previews**, usage, props, and variants — live on the
documentation site:

### 📖 [ersandip94.github.io/android-design-system](https://ersandip94.github.io/android-design-system/)

`CWSButton` · `CWSTextField` · `CWSCard` · `CWSBadge` / `CWSCountBadge` · `CWSChip` · `CWSDialog`
· `CWSBottomSheet` · `CWSTopBar` · `CWSNavigationBar` · `CWSCheckbox` / `CWSRadioButton` /
`CWSSwitch` / `CWSSlider`

---

## Accessibility

Helpers in `foundation/`:

```kotlin
Modifier.cwsClickable(role = Role.Button, onClickLabel = "open") { }   // 48dp target + semantics
Modifier.cwsSemantics(contentDescription = "Avatar", role = Role.Image)
Color(0xFF0C3A25).meetsContrastRatio(background = Color.White)          // WCAG check
```

A custom lint check, **`CWSMissingContentDescription`**, ships inside the AAR and flags `CWSButton`
usages that omit a `contentDescription`.

---

## Previews & screenshot tests

Every component ships `@Preview`s (light + dark). The design system is covered by **64 golden
images** via the official Compose Preview Screenshot Testing plugin:

```bash
./gradlew :core:updateDebugScreenshotTest    # record
./gradlew :core:validateDebugScreenshotTest  # verify
```

Screenshot test functions are annotated `@PreviewTest @LightDarkPreview @Composable`.

---

*Part of the [CodeWithSandip](../README.md) project · [codewithsandip.com](https://codewithsandip.com)*

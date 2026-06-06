# CodeWithSandip Design System (`:core`)

A Jetpack Compose component library: design tokens, theming (light / dark / custom brand), and a
curated set of accessible components — all prefixed `CWS`.

- **Artifact:** `com.codewithsandip:ds-core`
- **Brand color:** `#0C3A25` · **Min SDK:** 24 · 100% Kotlin + Compose

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

### CWSButton
Primary action button — variants, sizes, loading and disabled states, optional icons. 48dp touch
target, semantics built in.

```kotlin
CWSButton(
    text = "Continue",
    onClick = { },
    variant = CWSButtonVariant.Primary,   // Primary · Secondary · Ghost · Danger
    size = CWSButtonSize.Medium,           // Small · Medium · Large
    loading = false,
    leadingIcon = Icons.Default.Add,
    contentDescription = "Continue",
)
```

### CWSTextField
Outlined text input with label, placeholder, helper/error text, icons, and password masking.

```kotlin
CWSTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email",
    placeholder = "you@example.com",
    isError = emailError != null,
    errorText = emailError,
    leadingIcon = Icons.Default.Email,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
)
```

### CWSCard
Container surface — `Elevated`, `Outlined`, or `Filled`. Pass `onClick` to make the whole card a
single tappable target.

```kotlin
CWSCard(variant = CWSCardVariant.Elevated, onClick = { }) {
    Text("Title", style = MaterialTheme.typography.titleMedium)
    Text("Supporting text")
}
```

### CWSBadge / CWSCountBadge
Small status indicator — a dot, a label, or a number. `status` drives the color
(`Neutral · Info · Success · Warning · Error`).

```kotlin
CWSBadge(status = CWSBadgeStatus.Error)              // dot
CWSBadge(text = "New", status = CWSBadgeStatus.Info) // label
CWSCountBadge(count = 128, max = 99)                 // "99+"
```

### CWSChip
Compact chip — `Filter` (toggleable), `Input` (removable), or `Suggestion`.

```kotlin
CWSChip("All", onClick = { }, variant = CWSChipVariant.Filter, selected = true)
CWSChip("sandip@x.com", onClick = { }, variant = CWSChipVariant.Input, onClose = { })
CWSChip("Kotlin", onClick = { }, variant = CWSChipVariant.Suggestion)
```

### CWSDialog
Confirmation/alert dialog built from CWS buttons. `Destructive` styles the confirm action with the
danger color.

```kotlin
CWSDialog(
    onDismissRequest = { },
    title = "Delete project?",
    text = "This permanently removes the project and its data.",
    confirmLabel = "Delete",
    onConfirm = { },
    variant = CWSDialogVariant.Destructive, // Default · Destructive
)
```

### CWSBottomSheet
Modal bottom sheet for contextual actions/content.

```kotlin
CWSBottomSheet(onDismissRequest = { }) {
    Text("Share to", style = MaterialTheme.typography.titleMedium)
    CWSButton("Copy link", onClick = { }, variant = CWSButtonVariant.Secondary)
}
```

### CWSTopBar
Top app bar with a title, optional navigation icon, and trailing actions.

```kotlin
CWSTopBar(
    title = "Destinations",
    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
    onNavigationClick = { },
    actions = { IconButton(onClick = { }) { Icon(Icons.Default.MoreVert, "More") } },
)
```

### CWSNavigationBar
Bottom navigation with optional per-item badges.

```kotlin
CWSNavigationBar(
    items = listOf(
        CWSNavigationItem("Home", Icons.Default.Home),
        CWSNavigationItem("Favorites", Icons.Default.Favorite, badgeCount = 5),
        CWSNavigationItem("Settings", Icons.Default.Settings),
    ),
    selectedIndex = selected,
    onSelect = { selected = it },
)
```

### Toggles — CWSCheckbox · CWSRadioButton · CWSSwitch · CWSSlider
Selection controls with optional labels and correct accessibility roles.

```kotlin
CWSCheckbox(checked, onCheckedChange = { checked = it }, label = "Subscribe to updates")
CWSRadioButton(selected, onClick = { select() }, label = "Light")
CWSSwitch(checked, onCheckedChange = { checked = it }, label = "Wi-Fi")
CWSSlider(value, onValueChange = { value = it }, valueRange = 0f..1f, steps = 0)
```

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

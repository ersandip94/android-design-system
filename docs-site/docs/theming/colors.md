# Colors

Colors come in two layers: a raw **palette** (`CWSColors`) and **semantic roles**
(`CWSColorScheme`) that components actually read. Swap the scheme and everything re-themes.

## Palette — `CWSColors`

Derived from the brand color `#0C3A25`.

| Token | Hex | Usage |
|---|---|---|
| `Brand900` | `#0C3A25` | Primary brand |
| `Brand700` | `#155E3C` | Pressed / active |
| `Brand500` | `#1E8A57` | Default interactive |
| `Brand300` | `#5CB88A` | Subtle / secondary |
| `Brand100` | `#D4EFE2` | Surface tints |
| `Brand50`  | `#EDF8F3` | Light backgrounds |
| `Neutral900` | `#111827` | Primary text |
| `Neutral600` | `#4B5563` | Secondary text |
| `Neutral300` | `#D1D5DB` | Borders |
| `Error500` | `#DC2626` | Error |
| `Warning500` | `#D97706` | Warning |
| `Success500` | `#16A34A` | Success |

## Semantic roles — `CWSColorScheme`

Components read roles, never raw palette values:

`primary` · `onPrimary` · `primaryContainer` · `onPrimaryContainer` · `secondary` · `surface` ·
`onSurface` · `background` · `onBackground` · `error` · `onError` · `outline` · `outlineVariant` ·
`scrim`

```kotlin
val scheme = LocalCWSColorScheme.current
Box(Modifier.background(scheme.primaryContainer))
```

The defaults are built by `cwsLightColorScheme()` and `cwsDarkColorScheme()` — see
[Dark mode](dark-mode.md) and [Custom brand](custom-brand.md).

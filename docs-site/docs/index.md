# CWS Design System

A **Jetpack Compose** design system — design tokens, theming (light / dark / custom brand), and a
curated set of accessible components, all prefixed `CWS`.

<div class="grid cards" markdown>

- :material-palette: **Tokens & theming** — colors, spacing, type, shape, elevation, motion
- :material-widgets: **10+ components** — buttons, inputs, cards, chips, dialogs, navigation…
- :material-cellphone: **Compose-first** — every component ships `@Preview`s and golden tests
- :material-human: **Accessible** — 48dp targets, semantics, a custom lint check

</div>

---

## Hello, component

Wrap your UI in `CWSTheme` and use any `CWS*` component:

```kotlin
CWSTheme {
    CWSButton(text = "Get started", onClick = { /* … */ })
}
```

[Get started →](getting-started.md){ .md-button .md-button--primary }
[Browse components →](components/button.md){ .md-button }

---

*Built with Kotlin + Jetpack Compose · [codewithsandip.com](https://codewithsandip.com)*

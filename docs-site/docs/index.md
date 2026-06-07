# CWS Design System

A **Jetpack Compose** design system — design tokens, theming (light / dark / custom brand), and a
curated set of accessible components, all prefixed `CWS` — shipping inside a production-grade
**Clean Architecture + MVVM** sample app.

[Get started →](getting-started.md){ .md-button .md-button--primary }
[Browse components →](components/button.md){ .md-button }

---

## Explore

<div class="grid cards" markdown>

-   :material-puzzle-outline: **[API Design Philosophy](engineering/api-design.md)**

    ---

    Slot APIs, state hoisting, the `modifier` convention, enums over booleans, and bad-vs-good API
    at the call site.

-   :material-tag-multiple-outline: **[Versioning & Migration](engineering/versioning.md)**

    ---

    SemVer for a UI library, the Compose binary-compat gotcha, deprecation policy with
    `ReplaceWith`, changelogs, and migration guides.

-   :material-test-tube: **[Testing Strategy](engineering/testing.md)**

    ---

    64 light/dark golden screenshots, Compose UI tests, accessibility enforced four ways, and a
    custom lint rule that ships in the AAR.

-   :material-sitemap-outline: **[Architecture](architecture.md)**

    ---

    Feature-based modularization, the layered (Clean Architecture) view, the `login()` request
    lifecycle, and the module dependency graph.

-   :material-palette-outline: **[Theming](theming/colors.md)**

    ---

    Color tokens, dark mode, and re-skinning the entire system with a single custom brand color.

-   :material-widgets-outline: **[Components](components/button.md)**

    ---

    10+ accessible components — buttons, inputs, cards, chips, dialogs, navigation — each with
    light/dark previews.

</div>

---

## Hello, component

Wrap your UI in `CWSTheme` and use any `CWS*` component — it follows the system light/dark setting
by default:

```kotlin
CWSTheme {
    Column {
        CWSTextField(value = email, onValueChange = { email = it }, label = "Email")
        CWSButton(text = "Get started", onClick = { /* … */ })
    }
}
```

[Full setup guide →](getting-started.md){ .md-button }

---

## At a glance

<div class="grid cards" markdown>

- :material-palette: **Tokens & theming** — colors, spacing, type, shape, elevation, motion
- :material-widgets: **10+ components** — buttons, inputs, cards, chips, dialogs, navigation…
- :material-cellphone: **Compose-first** — every component ships `@Preview`s and golden tests
- :material-human: **Accessible** — 48dp targets, semantics, a custom lint check

</div>

---

*Built with Kotlin + Jetpack Compose · [codewithsandip.com](https://codewithsandip.com)*

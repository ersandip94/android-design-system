# CWS Design System + Sample App

A two-in-one Android project:

1. **CWS Design System** (`:core`) — a publishable Jetpack Compose component library with design
   tokens, theming (light / dark / custom brand), and accessible components, prefixed `CWS`.
2. **A sample app** that consumes it to demonstrate production-style, feature-based
   **Clean Architecture + MVVM** with **Hilt**, a mocked data layer, and Navigation Compose.

### 📖 Full documentation → <https://ersandip94.github.io/android-design-system/>

Everything below is the quick "run it locally" guide — the docs site is the deep reference for
**API design, versioning, testing, architecture, theming, and components**.

▶ **[Watch the app demo](assets/app-demo.mp4)** (`assets/app-demo.mp4`)

---

## Documentation

The repo's depth lives in the docs site — start here rather than duplicating it in the README:

| Page | What it covers |
|---|---|
| 🧩 [API Design Philosophy](https://ersandip94.github.io/android-design-system/engineering/api-design/) | Slot APIs, state hoisting, the `modifier` convention, enums over booleans, bad-vs-good API |
| 🏷️ [Versioning & Migration](https://ersandip94.github.io/android-design-system/engineering/versioning/) | SemVer for a UI library, deprecation policy, changelogs, migration guides |
| 🧪 [Testing Strategy](https://ersandip94.github.io/android-design-system/engineering/testing/) | 64 light/dark goldens, Compose UI tests, accessibility, custom lint |
| 🗺️ [Architecture](https://ersandip94.github.io/android-design-system/architecture/) | Layered (Clean Architecture) view, the `login()` request lifecycle, module graph |
| 🎨 [Theming](https://ersandip94.github.io/android-design-system/theming/colors/) | Color tokens, dark mode, custom brand re-skin |
| 🧱 [Components](https://ersandip94.github.io/android-design-system/components/button/) | 10+ components with light/dark previews |

---

## Highlights

- **Feature-based modularization + Clean Architecture** — features depend only on `:core` + `:domain`,
  never on each other or on `:data`. `:domain` is pure, annotation-free Kotlin.
- **Publishable design system** — `com.codewithsandip:ds-core`, with a custom accessibility lint check
  (`CWSMissingContentDescription`) shipped *inside the AAR*.
- **Tested like a product** — 64 screenshot goldens (light + dark), Compose UI tests, ViewModel +
  domain unit tests, and a tested lint detector.
- **Convention plugins** (`:buildlogic`) keep every module's build file ~10 lines.

---

## Demo login

| Field | Value |
|---|---|
| Email | `demo@codewithsandip.com` |
| Password | `password123` |

Or tap **Sign up** to register any account (kept in an in-memory mock store for the session).

---

## Build & run

```bash
# Build everything
./gradlew assembleDebug

# Install on a connected device/emulator
./gradlew :app:installDebug

# …or open in Android Studio and run the :app configuration
```

## Tests

```bash
./gradlew test testDebugUnitTest        # all JVM unit tests
./gradlew :core:validateDebugScreenshotTest   # verify screenshot goldens
./gradlew :core:lintDebug               # custom accessibility lint
```

> ⚠️ Instrumented (Compose UI) tests need an emulator on **API ≤ 36** — Espresso 3.7.0 reflects on
> the removed `InputManager.getInstance()` and fails on API 37. Unit and screenshot tests are
> unaffected. See the [Testing Strategy](https://ersandip94.github.io/android-design-system/engineering/testing/)
> for the full picture.

---

## Modules

| Module | Path | Purpose |
|---|---|---|
| `:app` | `app/` | Composition root — navigation host, theme switching, Hilt bootstrap |
| `:feature:auth` | `feature/auth/` | Login + Signup screens, ViewModels, nav graph |
| `:feature:home` | `feature/home/` | Destinations list + detail |
| `:core` | `core/` | Design system — components, theme, tokens ([README](core/README.md)) |
| `:domain` | `domain/` | Pure Kotlin — models, use cases, repository interfaces, validators |
| `:data` | `data/` | Repository impls, Hilt modules, mock JSON-backed APIs |
| `:lint-rules` | `lint-rules/` | Custom accessibility lint, bundled into `:core` |
| `:buildlogic` | `buildlogic/` | Gradle convention plugins |
| docs | `docs-site/` | MkDocs Material documentation site |

See [Architecture](https://ersandip94.github.io/android-design-system/architecture/) for the diagram
and dependency rules.

---

## Tech stack

| | |
|---|---|
| Language | Kotlin 2.2.10 |
| UI | Jetpack Compose (BOM 2026.05.01), Material 3 |
| Build | AGP 9.2.1, Gradle 9.4.1 |
| DI | Hilt 2.59.2 (+ KSP) |
| Async / Serialization | Coroutines + Flow · kotlinx.serialization |
| Navigation | Navigation Compose |
| Min / compile SDK | 24 / 36 (`:core`), 37 (app stack) |

---

## License

Apache License 2.0.

*CodeWithSandip — built with Kotlin + Jetpack Compose · [codewithsandip.com](https://codewithsandip.com)*

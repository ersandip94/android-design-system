# Versioning & Migration Strategy

A design system is a **contract**. The moment another team depends on `com.codewithsandip:ds-core`,
every public symbol becomes a promise. This page is that contract: how versions are numbered, what
counts as breaking, how APIs are retired, and how consumers migrate.

!!! info "Current release"
    **`com.codewithsandip:ds-core:1.0.0`** — version is the single source of truth in the root
    `VERSION` file, read by `CwsPublishPlugin`. The published artifact bundles the custom lint
    checks transitively (`lintPublish(project(":lint-rules"))`).

---

## 1. Semantic Versioning

We follow [SemVer 2.0.0](https://semver.org) — `MAJOR.MINOR.PATCH`:

| Bump | When | Example |
|---|---|---|
| **MAJOR** | A breaking change to the public API | remove `CWSButtonVariant.Ghost`, rename a param |
| **MINOR** | Backward-compatible additions | add `CWSButtonVariant.Link`, a new component |
| **PATCH** | Backward-compatible fixes | correct a disabled-state color, fix a crash |

For a UI library "breaking" is broader than most libraries — it includes both **source/binary**
compatibility *and* **visual/behavioral** compatibility:

- Removing or renaming a public symbol → **MAJOR**
- Changing a default that alters existing layouts (e.g. default `size` Medium → Large) → **MAJOR**
- Adding a new optional param *with a default* → **MINOR** (existing call sites unaffected)
- Adding a param *without* a default, or reordering params → **MAJOR**

!!! warning "Compose-specific gotcha"
    Adding a parameter — even with a default — is **source-compatible but binary-incompatible** for
    `@Composable` functions (the compiler changes the synthetic `$changed` bitmask and method
    signature). Consumers who don't recompile can break. Treat added params as MINOR *only* with a
    coordinated recompile; otherwise bundle them into the next MAJOR.

---

## 2. Guarding the API surface

Catching accidental breaks belongs in CI, not code review.

```kotlin
// Recommended: Kotlin binary-compatibility-validator
plugins {
    alias(libs.plugins.binaryCompatibilityValidator)
}
```

It dumps the public ABI to a checked-in `core/api/core.api` file. Any PR that changes the surface
fails `apiCheck` until the author runs `apiDump` and commits the diff — making every API change a
**visible, reviewable line in the PR**.

!!! note "Current status & next steps"
    The `binary-compatibility-validator` and `dokka` plugins are **declared in the version catalog
    but not yet enabled**. Enabling them is the planned hardening step before broad adoption:

    1. Apply the validator to `:core`, commit the generated `.api` dump.
    2. Add `apiCheck` to the CI workflow as a required check.
    3. Apply Dokka to `:core` to publish API reference docs alongside this site.

    Until then, [explicit API mode](api-design.md#1-naming-the-cws-prefix) (`explicitApi()`) is the
    first line of defence — it forces every public symbol to be intentional.

---

## 3. Deprecation policy

We **never** delete a public symbol without a deprecation cycle. The lifecycle:

```
v1.x  active  ──▶  v1.(x+n)  @Deprecated(WARNING) + ReplaceWith  ──▶  v2.0  removed
                          (at least one MINOR release of overlap)
```

Kotlin's `@Deprecated` with `ReplaceWith` lets the IDE auto-migrate call sites:

```kotlin
@Deprecated(
    message = "Use the variant parameter instead of isDanger.",
    replaceWith = ReplaceWith(
        "CWSButton(text, onClick, variant = CWSButtonVariant.Danger)"
    ),
    level = DeprecationLevel.WARNING,   // WARNING in 1.x → ERROR one minor before removal
)
@Composable
public fun CWSButton(text: String, onClick: () -> Unit, isDanger: Boolean) {
    CWSButton(text = text, onClick = onClick, variant =
        if (isDanger) CWSButtonVariant.Danger else CWSButtonVariant.Primary)
}
```

- **`WARNING`** when first deprecated — code still compiles, IDE offers *Alt+Enter → Replace with*.
- **`ERROR`** in the release before removal — forces the migration but the symbol still exists.
- **Removed** only at the next MAJOR.

!!! info "Current status"
    `:core` has **no `@Deprecated` symbols yet** — at `1.0.0` the surface is fresh. The example
    above is the template the first deprecation will follow.

---

## 4. Changelog

Every release ships a [Keep a Changelog](https://keepachangelog.com)-style `CHANGELOG.md`, grouped
by change type so consumers can scan for what affects them:

```markdown
## [1.1.0] — 2026-07-01
### Added
- `CWSButtonVariant.Link` for inline text actions.
- `CWSCard` now accepts `onLongClick`.

### Deprecated
- `CWSButton(isDanger = …)` — use `variant = CWSButtonVariant.Danger`. Removed in 2.0.

### Fixed
- Disabled button alpha now matches the 0.38 token in dark mode.
```

Breaking changes (MAJOR) additionally get a **migration section** — see below.

---

## 5. Migration example (a MAJOR upgrade)

When `2.0.0` removes the deprecated `isDanger` flag, the changelog links a migration guide:

=== "Before (1.x)"
    ```kotlin
    CWSButton(
        text = "Delete account",
        onClick = viewModel::delete,
        isDanger = true,
    )
    ```

=== "After (2.0)"
    ```kotlin
    CWSButton(
        text = "Delete account",
        onClick = viewModel::delete,
        variant = CWSButtonVariant.Danger,
    )
    ```

**How consumers migrate safely:**

1. Upgrade to the last `1.x` first — deprecation warnings light up every affected call site.
2. Apply the IDE's *Replace with* quick-fix (powered by `ReplaceWith`) across the project.
3. Build clean (no deprecation warnings) → bump to `2.0.0`.

This is why the deprecation overlap matters: a consumer is **never forced to rewrite and upgrade in
the same step**.

---

## Summary

- **SemVer**, with "breaking" widened to cover visual/behavioral and Compose binary changes.
- **explicitApi()** today; **binary-compatibility-validator** as the planned automated gate.
- **Deprecate → warn → error → remove**, never delete cold; `ReplaceWith` automates the rewrite.
- **Keep a Changelog** format; MAJOR releases ship a migration guide with before/after snippets.

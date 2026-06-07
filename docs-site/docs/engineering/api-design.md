# Component API Design Philosophy

A design system lives or dies by its **public API**. Components are written once but *called*
thousands of times, so the call site is the real product. This page documents the rules every
`CWS*` component follows — and the reasoning behind them.

!!! abstract "The one principle"
    **Optimize for the reader at the call site, not the author of the component.** A slightly
    harder component to *write* is worth it if it makes every *usage* obvious and hard to misuse.

---

## 1. Naming & the `CWS` prefix

Every public composable, enum, and token is prefixed `CWS` (`CWSButton`, `CWSButtonVariant`,
`CWSColors`). This isn't vanity:

- **No collisions** with Material 3's `Button`, `Card`, `Badge` — a consumer can `import` both and
  the intent is unambiguous at the call site.
- **Discoverability** — typing `CWS` in the IDE surfaces the entire system.
- **Grep-ability** — `git grep CWSButton` finds every usage across the app for migrations.

Public API is locked down with **Kotlin explicit API mode** (`explicitApi()` in
`CwsAndroidLibraryPlugin.kt`). Every exported declaration *must* state its visibility and return
type — you cannot leak an internal helper by forgetting `private`.

---

## 2. Parameter ordering & the `modifier` convention

Compose has a community convention; we follow it to the letter:

```kotlin
@Composable
public fun CWSButton(
    text: String,                                   // 1. required data
    onClick: () -> Unit,                            // 2. required callbacks
    modifier: Modifier = Modifier,                  // 3. modifier — FIRST optional param
    variant: CWSButtonVariant = CWSButtonVariant.Primary,  // 4. optional config…
    size: CWSButtonSize = CWSButtonSize.Medium,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    contentDescription: String? = null,
)
```

**The rules:**

1. **Required params first** — the caller can't forget them, and they read as positional args.
2. **`modifier: Modifier = Modifier`** is always the *first optional* param, so callers can pass a
   modifier positionally without naming every config flag.
3. **Optional config after**, all with sensible defaults, so the simplest call is one line:

```kotlin
CWSButton(text = "Continue", onClick = viewModel::submit)
```

!!! danger "Bad API"
    ```kotlin
    // modifier buried in the middle, no defaults, booleans for everything
    CWSButton(true, false, "Continue", Modifier, false, onClick)  // what is `true`? `false`?
    ```
    Positional booleans at the call site are unreadable and a refactor hazard.

---

## 3. State hoisting — components are stateless

Every interactive component is **fully stateless**: it renders the state it's given and reports
events upward. It never owns mutable state.

```kotlin
@Composable
public fun CWSTextField(
    value: String,                       // state in
    onValueChange: (String) -> Unit,     // events out
    modifier: Modifier = Modifier,
    label: String? = null,
    isError: Boolean = false,
    errorText: String? = null,
    // …
)
```

The caller owns the `value`; the field just renders it. This is what makes the system testable
(drive it from a `ViewModel`/`StateFlow`), previewable, and predictable — there's no hidden
internal `remember` that fights the source of truth.

| | Stateful (avoid) | Hoisted (our choice) |
|---|---|---|
| Source of truth | inside the component | owned by caller |
| Testable in isolation | hard | trivial |
| Drive from ViewModel | fights internal state | natural |
| Preview different states | impossible | pass the state in |

---

## 4. Slot APIs vs constrained parameters

The biggest API design decision is **how much freedom to give the caller**. We make it
deliberately, per component:

**Container components take a content slot** — they don't know or care what goes inside:

```kotlin
@Composable
public fun CWSCard(
    modifier: Modifier = Modifier,
    variant: CWSCardVariant = CWSCardVariant.Elevated,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,   // ← slot: caller composes the inside
)
```

`CWSCard` and `CWSBottomSheet` expose a `@Composable ColumnScope.() -> Unit` slot. The trailing
lambda position means they read naturally:

```kotlin
CWSCard {
    Text("Reykjavík")
    CWSBadge(text = "New", status = CWSBadgeStatus.Success)
}
```

**Leaf components take constrained parameters** — `CWSButton` accepts `leadingIcon: ImageVector?`,
not a `@Composable` slot. That's a *deliberate constraint*: a button should hold a label and an
icon, not arbitrary content. Constraining the API keeps every button in the app visually
consistent — you cannot accidentally jam a `Column` into a button.

!!! tip "The trade-off, stated plainly"
    **Slots = flexibility, constrained params = consistency.** Containers earn slots; leaf
    components stay constrained on purpose. The mark of a design system (vs. a widget grab-bag) is
    knowing where to *withhold* flexibility.

---

## 5. Enums over boolean explosion

Variants are modeled as **enums**, never as overlapping booleans:

```kotlin
public enum class CWSButtonVariant { Primary, Secondary, Ghost, Danger }
public enum class CWSButtonSize { Small, Medium, Large }
```

!!! danger "The boolean-explosion anti-pattern"
    ```kotlin
    CWSButton(isPrimary = true, isSecondary = false, isGhost = false, isDanger = true)
    ```
    What does `isPrimary = true, isDanger = true` even mean? Booleans allow **illegal states**.
    An enum makes the variants *mutually exclusive by construction* — the compiler enforces that a
    button is exactly one variant.

Enums also `when`-exhaust: add `CWSButtonVariant.Link` and every `when (variant)` without an
`else` becomes a compile error pointing you at the code to update.

---

## 6. Sensible defaults

The most common call should require the fewest arguments. Defaults encode the "house style":

- `variant = CWSButtonVariant.Primary` — the common case is the primary action.
- `enabled = true`, `loading = false` — the happy path.
- `dismissLabel: String? = "Cancel"` on `CWSDialog` — the conventional secondary action, overridable
  to `null` to hide it.

A default of `null` is meaningful too: `text: String? = null` on `CWSBadge` is the signal that
switches it from a labeled pill to a notification dot — one parameter, two well-defined behaviors.

---

## Checklist — does a new component's API pass?

- [ ] Prefixed `CWS`, public visibility explicit (`explicitApi`)
- [ ] Required params first; `modifier: Modifier = Modifier` is the first optional
- [ ] Stateless — state hoisted via `value` + `onValueChange`/`onClick`
- [ ] Slot (`content: @Composable …`) only if it's a *container*; constrained params for leaves
- [ ] Variants as enums, not booleans — no illegal states representable
- [ ] Simplest meaningful call fits on one line via defaults
- [ ] Accessibility hook present (`contentDescription` where applicable — enforced by [lint](testing.md#custom-lint-rules))

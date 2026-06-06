# Badge

`CWSBadge` — a small status indicator: a dot, a label, or (via `CWSCountBadge`) a number. The
`status` drives the color.

=== "Light"
    ![Badge](../assets/screenshots/badge-light.png){ width="360" }
=== "Dark"
    ![Badge](../assets/screenshots/badge-dark.png){ width="360" }

## Usage

```kotlin
CWSBadge(status = CWSBadgeStatus.Error)              // dot
CWSBadge(text = "New", status = CWSBadgeStatus.Info) // label
CWSCountBadge(count = 128, max = 99)                 // "99+"
```

## Status colors

`Neutral` · `Info` · `Success` · `Warning` · `Error`

| Component | Description |
|---|---|
| `CWSBadge` | Dot (no `text`) or pill label |
| `CWSCountBadge` | Numeric count, clamps to `"<max>+"` |

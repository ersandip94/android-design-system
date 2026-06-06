# Navigation bar

`CWSNavigationBar` — bottom navigation for switching between top-level destinations, with optional
per-item badges.

=== "Light"
    ![Navigation bar](../assets/screenshots/navigationbar-light.png){ width="480" }
=== "Dark"
    ![Navigation bar](../assets/screenshots/navigationbar-dark.png){ width="480" }

## Usage

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

## `CWSNavigationItem`

| Field | Type | Description |
|---|---|---|
| `label` | `String` | Text under the icon |
| `icon` | `ImageVector` | Destination icon |
| `badgeCount` | `Int?` | Optional notification badge |

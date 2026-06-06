# Get started

## 1. Add the dependency

```kotlin
dependencies {
    implementation("com.codewithsandip:ds-core:<version>")
}
```

Requirements: **Min SDK 24**, Kotlin + Jetpack Compose.

## 2. Wrap your app in `CWSTheme`

`CWSTheme` provides the design system's color scheme, typography, shapes, and spacing. It also
mirrors them into `MaterialTheme`, so any Material 3 components you use stay on-brand.

```kotlin
setContent {
    CWSTheme {
        // your UI
    }
}
```

By default the theme follows the system light/dark setting.

## 3. Use components

```kotlin
CWSTheme {
    Column {
        CWSTextField(value = email, onValueChange = { email = it }, label = "Email")
        CWSButton(text = "Continue", onClick = { /* … */ })
    }
}
```

## 4. Access theme values

From anywhere Material is in scope:

```kotlin
val primary = MaterialTheme.cws.colorScheme.primary
val gap     = MaterialTheme.cws.spacing.md   // 16.dp
```

!!! tip
    Re-skin the entire system with one line — pass a custom brand color to
    `cwsLightColorScheme(primaryBrand = …)`. See [Custom brand](theming/custom-brand.md).

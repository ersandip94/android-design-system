plugins {
    id("cws.component")
}

android {
    namespace = "com.codewithsandip.ds"
}

// SDK levels, Compose, explicit-API mode, common Compose + test dependencies, maven
// publishing, and the screenshot plugin all come from the cws.component convention plugin
// (see buildlogic/). Module-specific dependencies go here as the library grows.
dependencies {
    // Curated core icon set (Icons.Default.*) used by component icon slots, previews, and tests.
    implementation(libs.compose.material.icons.core)

    // Ships the design system's custom lint checks (e.g. missing contentDescription) to consumers.
    // isTransitive = false so only the lint jar is published, not kotlin-stdlib (lintPublish
    // accepts exactly one jar).
    lintPublish(project(":lint-rules")) {
        isTransitive = false
    }
}

plugins {
    id("cws.android.feature")
}

android {
    namespace = "com.codewithsandip.feature.auth"
}

// Compose, :core + :domain, ViewModel/Navigation, and test deps all come from the
// cws.android.feature convention plugin.

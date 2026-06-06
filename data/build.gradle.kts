plugins {
    id("cws.android.data")
    // Serialization for parsing the bundled mock JSON "responses".
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.codewithsandip.data"
}

// SDK levels, Java/JVM 17, Hilt, the :domain dependency, coroutines, and unit-test deps all come
// from the cws.android.data convention plugin.
dependencies {
    implementation(libs.kotlinx.serialization.json)
}

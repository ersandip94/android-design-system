plugins {
    id("cws.android.application")
}

android {
    namespace = "com.codewithsandip.codewithsandip_ds"

    defaultConfig {
        applicationId = "com.codewithsandip.codewithsandip_ds"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

// SDK levels, Java/JVM 17, Compose, the Compose UI/activity/lifecycle deps, and Compose test
// wiring all come from the cws.android.application convention plugin.
dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))

    // Material 3 XML window theme (Theme.Material3.DayNight.NoActionBar).
    implementation(libs.material)
}

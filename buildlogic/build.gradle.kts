import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.codewithsandip.buildlogic"

// AGP 9 / Kotlin 2.2 plugin APIs are compiled for JVM 17 — keep Java and Kotlin aligned.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.screenshot.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("cwsAndroidLibrary") {
            id = "cws.android.library"
            implementationClass = "CwsAndroidLibraryPlugin"
        }
        register("cwsPublish") {
            id = "cws.publish"
            implementationClass = "CwsPublishPlugin"
        }
        register("cwsComponent") {
            id = "cws.component"
            implementationClass = "CwsComponentPlugin"
        }
        register("cwsKotlinLibrary") {
            id = "cws.kotlin.library"
            implementationClass = "CwsKotlinLibraryPlugin"
        }
        register("cwsDataLibrary") {
            id = "cws.android.data"
            implementationClass = "CwsDataLibraryPlugin"
        }
        register("cwsFeature") {
            id = "cws.android.feature"
            implementationClass = "CwsFeaturePlugin"
        }
        register("cwsApplication") {
            id = "cws.android.application"
            implementationClass = "CwsApplicationPlugin"
        }
    }
}

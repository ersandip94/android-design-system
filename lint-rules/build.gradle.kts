import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `java-library`
    // Applied without a version: the Kotlin Gradle plugin is already on the classpath via the
    // `buildlogic` included build, so a versioned alias would clash.
    id("org.jetbrains.kotlin.jvm")
}

// Pure-JVM module hosting the design system's custom Android Lint checks. It is consumed by
// :core via `lintPublish`, which bundles these checks into the published AAR.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)

    testImplementation(libs.lint.api)
    testImplementation(libs.lint.tests)
    testImplementation(libs.junit)
}

tasks.jar {
    manifest {
        // Tells Lint where to find the registry of checks in this jar.
        attributes("Lint-Registry-v2" to "com.codewithsandip.ds.lint.CWSIssueRegistry")
    }
}

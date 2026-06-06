import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

/** Shared helpers used by the CodeWithSandip convention plugins to keep module build files thin. */

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/** Java source/target compatibility used across every module in the app stack. */
internal val APP_STACK_JAVA = JavaVersion.VERSION_17

/** Adds the Compose BOM + the standard CWS Compose UI dependencies to an Android module. */
internal fun Project.addComposeDependencies() {
    dependencies {
        val bom = platform(libs.findLibrary("compose-bom").get())
        add("implementation", bom)
        add("androidTestImplementation", bom)
        add("implementation", libs.findLibrary("compose-ui").get())
        add("implementation", libs.findLibrary("compose-foundation").get())
        add("implementation", libs.findLibrary("compose-material3").get())
        add("implementation", libs.findLibrary("compose-material-icons-core").get())
        add("implementation", libs.findLibrary("compose-ui-tooling-preview").get())
        add("debugImplementation", libs.findLibrary("compose-ui-tooling").get())
    }
}

/** Adds Compose UI test dependencies (unit + instrumented). */
internal fun Project.addComposeTestDependencies() {
    dependencies {
        add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
        add("androidTestImplementation", libs.findLibrary("compose-ui-test-junit4").get())
        add("debugImplementation", libs.findLibrary("compose-ui-test-manifest").get())
    }
}

/** Adds the standard JVM unit-test dependencies (JUnit + coroutines-test + turbine). */
internal fun Project.addUnitTestDependencies(includeTurbine: Boolean = false) {
    dependencies {
        add("testImplementation", libs.findLibrary("junit").get())
        add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
        if (includeTurbine) {
            add("testImplementation", libs.findLibrary("turbine").get())
        }
    }
}

/**
 * Applies Hilt + KSP and wires the runtime + compiler dependencies. KSP is applied first so the
 * `ksp(...)` configuration exists before we add the Hilt compiler to it.
 */
internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
        apply("com.google.dagger.hilt.android")
    }
    dependencies {
        add("implementation", libs.findLibrary("hilt-android").get())
        add("ksp", libs.findLibrary("hilt-android-compiler").get())
    }
}

/** Adds the design-system (:core) + domain (:domain) modules every feature depends on. */
internal fun Project.addFeatureModuleDependencies() {
    dependencies {
        add("implementation", project(":core"))
        add("implementation", project(":domain"))
        add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
        add("implementation", libs.findLibrary("androidx-navigation-compose").get())
        add("implementation", libs.findLibrary("androidx-hilt-navigation-compose").get())
        add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
    }
}

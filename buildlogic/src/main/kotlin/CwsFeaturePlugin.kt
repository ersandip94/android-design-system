import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Convention for a UI feature module (`:feature:*`): an Android library with Compose, the design
 * system (`:core`) and domain (`:domain`) on the classpath, plus ViewModel/Navigation/Compose-test
 * wiring. No `explicitApi` and no publishing — feature code is app-internal.
 */
class CwsFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<LibraryExtension> {
            compileSdk = 37
            defaultConfig {
                minSdk = 24
            }
            buildFeatures {
                compose = true
            }
            compileOptions {
                sourceCompatibility = APP_STACK_JAVA
                targetCompatibility = APP_STACK_JAVA
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }

        addComposeDependencies()
        addFeatureModuleDependencies()
        configureHilt()
        addUnitTestDependencies(includeTurbine = true)
        addComposeTestDependencies()
    }
}

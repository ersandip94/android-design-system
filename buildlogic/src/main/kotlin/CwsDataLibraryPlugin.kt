import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Convention for the data layer (`:data`): an Android library with built-in Kotlin (AGP 9), no
 * Compose. Depends on `:domain`, JVM 17, coroutines + unit-test deps wired.
 */
class CwsDataLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")

        extensions.configure<LibraryExtension> {
            compileSdk = 37
            defaultConfig {
                minSdk = 24
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

        dependencies {
            add("implementation", project(":domain"))
            add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
        }
        configureHilt()
        addUnitTestDependencies()
    }
}

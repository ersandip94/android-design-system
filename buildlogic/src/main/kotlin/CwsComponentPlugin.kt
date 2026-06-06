import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Convention for a publishable design-system component module: the Android library
 * base + maven publishing + Google's Compose Preview Screenshot Testing plugin.
 *
 * The experimental screenshot feature is enabled via the root gradle.properties flag
 * `android.experimental.enableScreenshotTest=true`.
 */
class CwsComponentPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("cws.android.library")
            apply("cws.publish")
            apply("com.android.compose.screenshot")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            // Compose Preview Screenshot tests render @Preview functions — they need ui-tooling.
            add("screenshotTestImplementation", libs.findLibrary("compose-ui-tooling").get())
            // Provides the @PreviewTest annotation the engine uses to discover screenshot tests.
            add("screenshotTestImplementation", libs.findLibrary("screenshot-validation-api").get())
        }
    }
}

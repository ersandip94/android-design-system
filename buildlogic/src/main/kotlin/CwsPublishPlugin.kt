import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

/**
 * Configures maven-publish for a CodeWithSandip library module: publishes the
 * `release` variant (with sources), stamps the version from the root VERSION file,
 * and attaches full POM metadata for codewithsandip.com.
 *
 * Signing + the Sonatype/Maven Central repository are wired in Phase 10; without them
 * `publishReleasePublicationToMavenLocal` works out of the box.
 */
class CwsPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("maven-publish")

        val dsVersion = rootProject.file("VERSION")
            .takeIf { it.exists() }
            ?.readText()
            ?.trim()
            ?.ifEmpty { null }
            ?: "1.0.0-SNAPSHOT"

        extensions.configure<LibraryExtension> {
            publishing {
                singleVariant("release") {
                    withSourcesJar()
                }
            }
        }

        afterEvaluate {
            extensions.configure<PublishingExtension> {
                publications {
                    create<MavenPublication>("release") {
                        from(components.getByName("release"))
                        groupId = "com.codewithsandip"
                        artifactId = "ds-core"
                        version = dsVersion
                        pom {
                            name.set("CodeWithSandip Design System")
                            description.set("Jetpack Compose design system for Android")
                            url.set("https://codewithsandip.com")
                            licenses {
                                license {
                                    name.set("The Apache License, Version 2.0")
                                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                                }
                            }
                            developers {
                                developer {
                                    id.set("codewithsandip")
                                    name.set("CodeWithSandip")
                                    url.set("https://codewithsandip.com")
                                }
                            }
                            scm {
                                connection.set("scm:git:https://github.com/codewithsandip/codewithsandip-ds.git")
                                developerConnection.set("scm:git:ssh://git@github.com/codewithsandip/codewithsandip-ds.git")
                                url.set("https://github.com/codewithsandip/codewithsandip-ds")
                            }
                        }
                    }
                }
            }
        }
    }
}

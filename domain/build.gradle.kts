plugins {
    id("cws.kotlin.library")
}

// Pure-Kotlin domain layer: models, repository interfaces, use cases. All shared config
// (JVM 17, coroutines, JUnit) comes from the cws.kotlin.library convention plugin.

package com.codewithsandip.data.remote

import kotlinx.serialization.json.Json

/** Reads and decodes a bundled mock JSON resource from the classpath (e.g. "/mock/users.json"). */
internal object MockJson {

    val json = Json { ignoreUnknownKeys = true }

    inline fun <reified T> read(resourcePath: String): T {
        val stream = MockJson::class.java.getResourceAsStream(resourcePath)
            ?: error("Mock resource not found on classpath: $resourcePath")
        val text = stream.bufferedReader().use { it.readText() }
        return json.decodeFromString(text)
    }
}

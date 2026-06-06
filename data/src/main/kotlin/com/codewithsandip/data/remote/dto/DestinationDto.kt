package com.codewithsandip.data.remote.dto

import com.codewithsandip.domain.model.Destination
import kotlinx.serialization.Serializable

/** Wire representation of a destination, deserialized from the mock JSON "response". */
@Serializable
data class DestinationDto(
    val id: String,
    val name: String,
    val country: String,
    val summary: String,
    val rating: Double,
    val priceFromUsd: Int,
    val tags: List<String>,
)

fun DestinationDto.toDomain(): Destination = Destination(
    id = id,
    name = name,
    country = country,
    summary = summary,
    rating = rating,
    priceFromUsd = priceFromUsd,
    tags = tags,
)

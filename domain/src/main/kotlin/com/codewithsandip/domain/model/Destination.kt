package com.codewithsandip.domain.model

/** A travel destination shown in the home feature. */
data class Destination(
    val id: String,
    val name: String,
    val country: String,
    val summary: String,
    val rating: Double,
    val priceFromUsd: Int,
    val tags: List<String>,
)

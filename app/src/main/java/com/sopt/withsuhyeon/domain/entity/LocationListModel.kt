package com.sopt.withsuhyeon.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class LocationListModel (
    val location: String,
    val subLocations: List<String>
)
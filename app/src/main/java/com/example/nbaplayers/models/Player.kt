package com.example.nbaplayers.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName:String,
    val number: Int,
    val position: String,
    val id: Int
)

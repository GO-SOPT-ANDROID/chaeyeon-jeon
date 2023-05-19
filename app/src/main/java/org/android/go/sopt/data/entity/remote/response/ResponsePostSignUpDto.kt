package org.android.go.sopt.data.entity.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostSignUpDto(
    @SerialName("name")
    val name: String,
    @SerialName("skill")
    val skill: String,
)

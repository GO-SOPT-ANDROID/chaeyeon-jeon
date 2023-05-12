package org.android.go.sopt.data.entity.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostSignInDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)

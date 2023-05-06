package org.android.go.sopt.data.entity.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostSignUpDto(
    val name: String,
    val skill: String,
)

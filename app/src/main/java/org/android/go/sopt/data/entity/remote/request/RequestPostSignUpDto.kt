package org.android.go.sopt.data.entity.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestPostSignUpDto(
    val id: String,
    val password: String,
    val name: String,
    val skill: String,
)

package org.android.go.sopt.data.entity.remote.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    @SerialName("data")
    val data: T,
)

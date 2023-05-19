package org.android.go.sopt.data.entity.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.domain.model.Follower

@Serializable
data class ResponseGetFollowerListDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<Follower>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class Follower(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("avatar")
        val avatar: String,
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    fun toFollower() = data.map { follower ->
        Follower(
            id = follower.id,
            name = "${follower.firstName} ${follower.lastName}",
            profile = follower.avatar,
            email = follower.email,
        )
    }
}

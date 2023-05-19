package org.android.go.sopt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val pwd: String = "",
    val name: String = "익명",
    val specialty: String? = "",
) : Parcelable

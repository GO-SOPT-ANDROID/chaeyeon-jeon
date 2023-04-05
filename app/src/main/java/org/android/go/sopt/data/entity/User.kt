package org.android.go.sopt.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.go.sopt.util.type.MBTI

@Parcelize
data class User(
    val id: String = "",
    val pwd: String = "",
    val name: String? = "",
    val specialty: String? = "",
    val mbti: MBTI? = null,
) : Parcelable

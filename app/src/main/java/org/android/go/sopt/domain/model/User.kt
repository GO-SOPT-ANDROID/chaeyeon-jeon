package org.android.go.sopt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.go.sopt.util.type.MBTI
import org.android.go.sopt.util.type.MBTI.NONE

@Parcelize
data class User(
    val id: String = "",
    val pwd: String = "",
    val name: String? = "익명",
    val specialty: String? = "",
    val mbti: MBTI? = NONE,
) : Parcelable

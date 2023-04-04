package org.android.go.sopt.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.go.sopt.util.type.MBTI

@Parcelize
data class User(
    val id: String = "",
    val pwd: String = "",
    val name: String? = null,
    val specialty: String? = null,
    val mbti: MBTI? = null,
) : Parcelable

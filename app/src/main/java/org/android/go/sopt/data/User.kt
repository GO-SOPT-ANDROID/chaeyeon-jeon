package org.android.go.sopt.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.go.sopt.util.type.MBTI

@Parcelize
data class User(
    val id: String,
    val password: String,
    val name: String,
    val specialty: String,
    val mbti: MBTI
) : Parcelable

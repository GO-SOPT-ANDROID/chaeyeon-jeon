package org.android.go.sopt.domain

import org.android.go.sopt.data.entity.User

interface AuthRepository {
    fun setAutoLogin(isAutoLogin: Boolean)

    fun getAutoLogin(): Boolean

    fun setSignedUpUser(user: User)

    fun getSignedUpUser(): User?
}

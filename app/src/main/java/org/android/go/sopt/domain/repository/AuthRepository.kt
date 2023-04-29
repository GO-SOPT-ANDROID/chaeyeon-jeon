package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.model.User

interface AuthRepository {
    fun setAutoLogin(isAutoLogin: Boolean)

    fun getAutoLogin(): Boolean

    fun setSignedUpUser(user: User)

    fun getSignedUpUser(): User?

    fun clearLocalPref()
}

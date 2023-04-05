package org.android.go.sopt.data.repository

import org.android.go.sopt.data.entity.User
import org.android.go.sopt.data.source.LocalPrefDataSource
import org.android.go.sopt.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localPrefDataSource: LocalPrefDataSource,
) : AuthRepository {
    override fun setAutoLogin(isAutoLogin: Boolean) {
        localPrefDataSource.isAutoLogin = isAutoLogin
    }

    override fun getAutoLogin(): Boolean = localPrefDataSource.isAutoLogin

    override fun setSignedUpUser(user: User) {
        localPrefDataSource.signedUpUser = user
    }

    override fun getSignedUpUser(): User? = localPrefDataSource.signedUpUser
}

package org.android.go.sopt.data.source.remote

import org.android.go.sopt.data.entity.remote.request.RequestPostSignInDto
import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignInDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.entity.remote.response.wrapper.BaseResponse
import org.android.go.sopt.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun postSignup(
        requestPostSignUpDto: RequestPostSignUpDto,
    ): BaseResponse<ResponsePostSignUpDto> =
        authService.postSignUp(requestPostSignUpDto)

    suspend fun postSignIn(
        requestPostSignInDto: RequestPostSignInDto,
    ): BaseResponse<ResponsePostSignInDto> =
        authService.postSignIn(requestPostSignInDto)
}

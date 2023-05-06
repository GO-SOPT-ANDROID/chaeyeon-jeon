package org.android.go.sopt.data.source

import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.entity.remote.wrapper.BaseResponse
import org.android.go.sopt.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun postSignup(
        requestPostSignUpDto: RequestPostSignUpDto,
    ): BaseResponse<ResponsePostSignUpDto> =
        authService.postSignUp(requestPostSignUpDto)
}

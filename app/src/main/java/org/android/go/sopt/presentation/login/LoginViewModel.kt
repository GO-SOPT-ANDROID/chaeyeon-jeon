package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.data.entity.remote.request.RequestPostSignInDto
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.state.LocalUiState
import org.android.go.sopt.util.state.LocalUiState.Failure
import org.android.go.sopt.util.state.LocalUiState.Success
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginState = MutableLiveData<LocalUiState>()
    val loginState: LiveData<LocalUiState>
        get() = _loginState

    val _id = MutableLiveData("")
    private val id: String
        get() = _id.value?.trim() ?: ""

    val _pwd = MutableLiveData("")
    private val pwd: String
        get() = _pwd.value?.trim() ?: ""

    init {
        setupAutoLogin()
    }

    private fun setupAutoLogin() {
        if (authRepository.getAutoLogin() && authRepository.getSignedUpUser() != null) {
            _loginState.value = Success
        }
    }

    private fun isValidInput() = id.isNotBlank() && pwd.isNotBlank()

    fun login() {
        if (!isValidInput()) {
            _loginState.value = Failure(null)
            return
        }

        val requestPostSignInDto = RequestPostSignInDto(
            id = id,
            password = pwd,
        )
        viewModelScope.launch {
            authRepository.postSignin(requestPostSignInDto)
                .onSuccess { response ->
                    authRepository.setSignedUpUser(
                        User(
                            id = requestPostSignInDto.id,
                            pwd = requestPostSignInDto.password,
                            name = requireNotNull(response).name,
                            specialty = response.skill,
                        ),
                    )
                    authRepository.setAutoLogin(true)
                    _loginState.value = Success
                    Timber.d("POST SIGNIN SUCCESS : $response")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        Timber.e("POST SIGNIN FAIL ${t.code()} : ${t.message()}")
                    }
                }
        }
    }
}

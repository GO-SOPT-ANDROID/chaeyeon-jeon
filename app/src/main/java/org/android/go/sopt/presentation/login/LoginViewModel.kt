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
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Loading
import org.android.go.sopt.util.state.RemoteUiState.Success
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginState = MutableLiveData<RemoteUiState>()
    val loginState: LiveData<RemoteUiState>
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
            _loginState.value = RemoteUiState.Success
        }
    }

    fun login() {
        viewModelScope.launch {
            _loginState.value = Loading

            if (!isValidInput()) {
                _loginState.value = Failure(CODE_INVALID_INPUT)
                return@launch
            }

            val requestPostSignInDto = RequestPostSignInDto(
                id = id,
                password = pwd,
            )
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
                        when (t.code()) {
                            CODE_INVALID_INPUT -> _loginState.value = Failure(CODE_INVALID_INPUT)
                            else -> _loginState.value = Error
                        }
                        Timber.e("POST SIGNIN FAIL ${t.code()} : ${t.message()}")
                    }
                }
        }
    }

    private fun isValidInput() = id.isNotBlank() && pwd.isNotBlank()

    companion object {
        const val CODE_INVALID_INPUT = 400
    }
}

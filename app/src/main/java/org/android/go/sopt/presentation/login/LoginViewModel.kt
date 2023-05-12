package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.entity.local.User
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.state.LocalUiState
import org.android.go.sopt.util.state.LocalUiState.Failure
import org.android.go.sopt.util.state.LocalUiState.Success
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    var signedUpUser = User()

    private val _loginState = MutableLiveData<LocalUiState>()
    val loginState: LiveData<LocalUiState>
        get() = _loginState

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")

    init {
        setupAutoLogin()
    }

    private fun setupAutoLogin() {
        if (authRepository.getAutoLogin() && authRepository.getSignedUpUser() != null) _loginState.value = Success
    }

    fun setSavedUser(savedUser: User) {
        this.signedUpUser = savedUser
    }

    private fun isValidInput() =
        !id.value.isNullOrBlank() && id.value == signedUpUser.id && !pwd.value.isNullOrBlank() && pwd.value == signedUpUser.pwd

    fun login() {
        if (!isValidInput()) {
            _loginState.value = Failure(null)
            return
        }
        authRepository.setAutoLogin(true)
        _loginState.value = Success
    }
}

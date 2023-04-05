package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.domain.AuthRepository
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    var signedUpUser = User()

    private val _loginState = MutableLiveData<UiState>()
    val loginState: LiveData<UiState>
        get() = _loginState

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")

    init {
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
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
            authRepository.setAutoLogin(true)
            return
        }
        _loginState.value = Success
    }
}

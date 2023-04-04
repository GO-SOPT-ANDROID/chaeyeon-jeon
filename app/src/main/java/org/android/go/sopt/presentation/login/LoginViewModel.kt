package org.android.go.sopt.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.User
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginState = MutableLiveData<UiState>()
    val loginState: LiveData<UiState>
        get() = _loginState

    private lateinit var savedUser: User

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")

    fun setSavedUser(savedUser: User) {
        this.savedUser = savedUser
    }

    private fun isValidInput() =
        !id.value.isNullOrBlank() && id.value == savedUser.id && !pwd.value.isNullOrBlank() && pwd.value == savedUser.pwd

    fun login() {
        if (!isValidInput()) _loginState.value = Failure(0)
        _loginState.value = Success
    }
}

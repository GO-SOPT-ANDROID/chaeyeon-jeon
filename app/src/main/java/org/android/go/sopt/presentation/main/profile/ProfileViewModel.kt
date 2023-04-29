package org.android.go.sopt.presentation.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val signedUpUser = MutableLiveData<User>()

    init {
        getSignedUpUser()
    }

    private fun getSignedUpUser() {
        signedUpUser.value = authRepository.getSignedUpUser() ?: User()
    }

    fun clearLocalPref() {
        authRepository.clearLocalPref()
    }
}

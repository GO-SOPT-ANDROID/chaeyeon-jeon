package org.android.go.sopt.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.domain.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val signedUpUser = MutableLiveData<User>()

    init {
        signedUpUser.value = getSignedUpUser()
    }

    private fun getSignedUpUser(): User = authRepository.getSignedUpUser() ?: User()

    fun clearLocalPref() {
        authRepository.clearLocalPref()
    }
}
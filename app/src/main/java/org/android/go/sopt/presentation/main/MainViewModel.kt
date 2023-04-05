package org.android.go.sopt.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.domain.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun getSignedUpUser(): User = authRepository.getSignedUpUser() ?: User()
}

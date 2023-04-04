package org.android.go.sopt.util

sealed class UiState {
    object Success : UiState()
    data class Failure(val code: Int?) : UiState()
//    object Error : UiState()
}

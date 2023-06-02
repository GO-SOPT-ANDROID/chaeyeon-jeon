package org.android.go.sopt.util.state

sealed class LocalUiState {
    object Loading : LocalUiState()
    object Success : LocalUiState()
    data class Failure(val code: Int?) : LocalUiState()
}

package org.android.go.sopt.util.state

sealed class RemoteUiState {
    object Success : RemoteUiState()
    data class Failure(val code: Int?) : RemoteUiState()
    object Error : RemoteUiState()
}

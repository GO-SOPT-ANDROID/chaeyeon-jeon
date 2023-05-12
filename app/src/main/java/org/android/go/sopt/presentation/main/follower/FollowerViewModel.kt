package org.android.go.sopt.presentation.main.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.domain.repository.FollowerRepository
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Success
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(
    private val followerRepository: FollowerRepository,
) : ViewModel() {
    private val _followerList = MutableLiveData<List<Follower>>()
    val followerList: List<Follower>?
        get() = _followerList.value

    private val _getFollowerListState = MutableLiveData<RemoteUiState>()
    val getFollowerListState: LiveData<RemoteUiState>
        get() = _getFollowerListState

    init {
        getFollowerList()
    }

    private fun getFollowerList() {
        Timber.d("get follower list 시작")
        viewModelScope.launch {
            followerRepository.getFollowerList(1)
                .onSuccess { response ->
                    // TODO: followerList null 처리
                    _followerList.value = response
                    _getFollowerListState.value = Success
                    Timber.d("GET FOLLOWER LIST SUCCESS : $response")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        // TODO : failure 예외 처리
                        _getFollowerListState.value = Error
                        Timber.e("GET FOLLOWER LIST FAIL : ${t.message}")
                    }
                }
        }
    }
}

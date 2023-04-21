package org.android.go.sopt.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.model.Repo
import org.android.go.sopt.domain.repository.RepoRepository
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoRepository: RepoRepository,
) : ViewModel() {
    private val _repoList = MutableLiveData<List<Repo>>()
    val repoList: LiveData<List<Repo>>
        get() = _repoList

    private val _getRepoListState = MutableLiveData<UiState>()
    val getRepoListState: LiveData<UiState>
        get() = _getRepoListState

    init {
        getRepoList()
    }

    private fun getRepoList() {
        viewModelScope.launch {
            repoRepository.getRepoList()
                .onSuccess { repoList ->
                    // TODO : repoList null 처리
                    _repoList.value = repoList
                    _getRepoListState.value = Success
                    Timber.d("GET REPO LIST SUCCESS : $repoList")
                }
                .onFailure { t ->
                    Timber.e("GET REPO LIST FAIL : ${t.message}")
                    _getRepoListState.value = Failure(null)
                }
        }
    }
}

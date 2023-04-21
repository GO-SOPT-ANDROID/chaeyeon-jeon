package org.android.go.sopt.presentation.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.domain.repository.RepoRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repoRepository: RepoRepository,
) : ViewModel() {
    // TODO : getRepoList 호출 및 UI 보여주는 로직 작성
}

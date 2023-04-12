package org.android.go.sopt.presentation.main.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.R
import org.android.go.sopt.data.entity.Repo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val mockRepoList = listOf<Repo>(
        Repo(
            image = R.drawable.img_main_profile,
            name = "Keyneez",
            author = "Chaeyeon",
        ),
        Repo(
            image = R.drawable.img_main_profile,
            name = "Keyneez-Release",
            author = "Chaeyeon",
        ),
        Repo(
            image = R.drawable.img_main_profile,
            name = "Algorithm-Study",
            author = "Chaeyeon",
        ),
        Repo(
            image = R.drawable.img_main_profile,
            name = "Kotlin-Study",
            author = "Chaeyeon",
        ),
    )
}

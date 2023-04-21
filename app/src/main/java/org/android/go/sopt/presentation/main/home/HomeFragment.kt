package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.showSnackbar

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    private val repoHeaderAdapter by lazy { RepoHeaderAdapter() }
    private val repoItemAdapter by lazy { RepoItemAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRepoListAdapter()
        setupGetRepoListState()
    }

    private fun initRepoListAdapter() {
        binding.rvHomeRepo.adapter = ConcatAdapter(repoHeaderAdapter, repoItemAdapter)
    }

    private fun setupGetRepoListState() {
        viewModel.getRepoListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> {
                    repoItemAdapter.submitList(viewModel.repoList.value)
                }
                is Failure -> {
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.home_get_repo_list_fail_msg),
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

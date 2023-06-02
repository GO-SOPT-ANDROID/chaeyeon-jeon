package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.dialog.LoadingDialogFragment
import org.android.go.sopt.presentation.login.LoginActivity.Companion.TAG_LOADING_DIALOG
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.state.LocalUiState.Failure
import org.android.go.sopt.util.state.LocalUiState.Loading
import org.android.go.sopt.util.state.LocalUiState.Success

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    private var repoHeaderAdapter: RepoHeaderAdapter? = null
    private var repoItemAdapter: RepoItemAdapter? = null

    private var _loadingDialog: LoadingDialogFragment? = null
    private val loadingDialog
        get() = requireNotNull(_loadingDialog) { getString(R.string.dialog_not_initialized_error_msg) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRepoAdapter()
        initLoadingDialogFragment()
        setupGetRepoListState()
    }

    private fun initRepoAdapter() {
        repoHeaderAdapter = RepoHeaderAdapter()
        repoItemAdapter = RepoItemAdapter()
        binding.rvHomeRepo.adapter = ConcatAdapter(repoHeaderAdapter, repoItemAdapter)
    }

    private fun initLoadingDialogFragment() {
        _loadingDialog = LoadingDialogFragment()
    }

    private fun setupGetRepoListState() {
        viewModel.getRepoListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Loading -> loadingDialog.show(parentFragmentManager, TAG_LOADING_DIALOG)
                is Success -> {
                    if (loadingDialog.isAdded) loadingDialog.dismiss()
                    repoItemAdapter?.submitList(viewModel.repoList.value)
                }

                is Failure -> {
                    if (loadingDialog.isAdded) loadingDialog.dismiss()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.home_get_repo_list_fail_msg),
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        repoHeaderAdapter = null
        repoItemAdapter = null
        _loadingDialog = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

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

    private var _repoItemAdapter: RepoItemAdapter? = null
    private val repoItemAdapter
        get() = requireNotNull(_repoItemAdapter) { getString(R.string.adapter_not_initialized_error_msg) }

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
        _repoItemAdapter = RepoItemAdapter()
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
                    dismissLoadingDialog()
                    repoItemAdapter.submitList(viewModel.repoList.value)
                }

                is Failure -> {
                    dismissLoadingDialog()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.home_get_repo_list_fail_msg),
                    )
                }
            }
        }
    }

    private fun dismissLoadingDialog() {
        if (!loadingDialog.isAdded) return
        loadingDialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        repoHeaderAdapter = null
        _repoItemAdapter = null
        _loadingDialog = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

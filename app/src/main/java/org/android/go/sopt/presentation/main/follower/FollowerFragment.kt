package org.android.go.sopt.presentation.main.follower

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentFollowerBinding
import org.android.go.sopt.presentation.dialog.LoadingDialogFragment
import org.android.go.sopt.presentation.login.LoginActivity.Companion.TAG_LOADING_DIALOG
import org.android.go.sopt.presentation.main.follower.FollowerAdapter.Companion.VIEW_TYPE_HEADER
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Loading
import org.android.go.sopt.util.state.RemoteUiState.Success

@AndroidEntryPoint
class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private val viewModel by viewModels<FollowerViewModel>()

    private var _followerAdapter: FollowerAdapter? = null
    private val followerAdapter
        get() = requireNotNull(_followerAdapter) { getString(R.string.adapter_not_initialized_error_msg) }

    private var _loadingDialog: LoadingDialogFragment? = null
    private val loadingDialog
        get() = requireNotNull(_loadingDialog) { getString(R.string.dialog_not_initialized_error_msg) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initFollowerAdapter()
        initLoadingDialogFragment()
        initRecyclerViewLayoutManager()
        setupGetFollowerListState()
    }

    private fun initFollowerAdapter() {
        _followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }

    private fun initLoadingDialogFragment() {
        _loadingDialog = LoadingDialogFragment()
    }

    private fun initRecyclerViewLayoutManager() {
        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (followerAdapter.getItemViewType(position)) {
                    VIEW_TYPE_HEADER -> 2
                    else -> 1
                }
            }
        }
        binding.rvFollower.layoutManager = layoutManager
    }

    private fun setupGetFollowerListState() {
        viewModel.getFollowerListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Loading -> {
                    loadingDialog.show(parentFragmentManager, TAG_LOADING_DIALOG)
                }

                is Success -> {
                    dismissLoadingDialog()
                    followerAdapter.submitList(viewModel.followerList)
                }

                is Failure -> {
                    dismissLoadingDialog()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.follower_get_follower_list_null_msg),
                    )
                }

                is Error -> {
                    dismissLoadingDialog()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.unknown_error_msg),
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
        _followerAdapter = null
        _loadingDialog = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FollowerFragment()
    }
}

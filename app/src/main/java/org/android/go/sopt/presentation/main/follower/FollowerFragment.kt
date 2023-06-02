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

    private var followerAdapter: FollowerAdapter? = null
    private var loadingDialog: LoadingDialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initFollowerAdapter()
        initLoadingDialogFragment()
        initRecyclerViewLayoutManager()
        setupGetFollowerListState()
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
    }

    private fun initLoadingDialogFragment() {
        loadingDialog = LoadingDialogFragment()
    }

    private fun initRecyclerViewLayoutManager() {
        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (followerAdapter?.getItemViewType(position)) {
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
                is Loading -> loadingDialog?.show(parentFragmentManager, TAG_LOADING_DIALOG)
                is Success -> {
                    loadingDialog?.dismiss()
                    followerAdapter?.submitList(viewModel.followerList)
                }

                is Failure -> {
                    loadingDialog?.dismiss()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.follower_get_follower_list_null_msg),
                    )
                }

                is Error -> {
                    loadingDialog?.dismiss()
                    requireContext().showSnackbar(
                        binding.root,
                        getString(R.string.unknown_error_msg),
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        followerAdapter = null
        loadingDialog = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FollowerFragment()
    }
}

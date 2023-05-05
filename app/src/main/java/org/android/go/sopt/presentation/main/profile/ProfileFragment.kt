package org.android.go.sopt.presentation.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentProfileBinding
import org.android.go.sopt.presentation.main.profile.dialog.LogoutDialogFragment
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initLogoutBtnClickListener()
    }

    private fun initLogoutBtnClickListener() {
        binding.btnProfileLogout.setOnSingleClickListener {
            LogoutDialogFragment().show(requireActivity().supportFragmentManager, TAG_LOGOUT_DIALOG)
        }
    }

    companion object {
        private const val TAG_LOGOUT_DIALOG = "logoutDialog"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}

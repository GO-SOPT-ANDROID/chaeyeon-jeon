package org.android.go.sopt.presentation.main.profile.dialog

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentLogoutDialogBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.presentation.main.profile.ProfileViewModel
import org.android.go.sopt.util.binding.BindingDialogFragment
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class LogoutDialogFragment :
    BindingDialogFragment<FragmentLogoutDialogBinding>(R.layout.fragment_logout_dialog) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initCancelBtnClickListener()
        initLogoutBtnClickListener()
    }

    private fun initCancelBtnClickListener() {
        binding.btnLogoutDialogCancel.setOnSingleClickListener {
            dismiss()
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.btnLogoutDialogLogout.setOnSingleClickListener {
            viewModel.clearLocalPref()
            requireContext().showToast(getString(R.string.profile_logout_msg))
            intentToLogin()
        }
    }

    private fun intentToLogin() {
        Intent(activity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogoutDialogFragment()
    }
}

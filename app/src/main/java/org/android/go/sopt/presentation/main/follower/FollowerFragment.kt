package org.android.go.sopt.presentation.main.follower

import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentFollowerBinding
import org.android.go.sopt.util.binding.BindingFragment

class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    companion object {
        @JvmStatic
        fun newInstance() = FollowerFragment()
    }
}

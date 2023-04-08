package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val repoAdapter by lazy { RepoAdapter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRepoListAdapter()
    }

    private fun initRepoListAdapter() {
        with (binding) {
            rvHomeRepo.adapter = repoAdapter
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

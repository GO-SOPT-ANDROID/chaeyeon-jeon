package org.android.go.sopt.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.HeaderHomeRepoBinding
import org.android.go.sopt.presentation.main.home.RepoHeaderAdapter.RepoHeaderViewHolder

class RepoHeaderAdapter : RecyclerView.Adapter<RepoHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHeaderViewHolder {
        return RepoHeaderViewHolder(
            HeaderHomeRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: RepoHeaderViewHolder, position: Int) {}

    override fun getItemCount(): Int = HEADER_COUNT

    class RepoHeaderViewHolder(private val binding: HeaderHomeRepoBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val HEADER_COUNT = 1
    }
}

package org.android.go.sopt.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.entity.MockRepoDto
import org.android.go.sopt.databinding.ItemHomeRepoBinding
import org.android.go.sopt.util.DiffCallback

class RepoItemAdapter :
    ListAdapter<MockRepoDto, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(
            ItemHomeRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) holder.setRepo(getItem(position))
    }

    class RepoViewHolder(private val binding: ItemHomeRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRepo(repo: MockRepoDto) {
            binding.data = repo
        }
    }

    companion object {
        private val diffUtil = DiffCallback<MockRepoDto>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new },
        )
    }
}

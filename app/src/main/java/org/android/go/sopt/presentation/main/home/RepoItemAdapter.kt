package org.android.go.sopt.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemHomeRepoBinding
import org.android.go.sopt.domain.model.Repo
import org.android.go.sopt.util.DiffCallback

class RepoItemAdapter :
    ListAdapter<Repo, RecyclerView.ViewHolder>(diffUtil) {
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
        fun setRepo(repo: Repo) {
            binding.data = repo
        }
    }

    companion object {
        private val diffUtil = DiffCallback<Repo>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new },
        )
    }
}

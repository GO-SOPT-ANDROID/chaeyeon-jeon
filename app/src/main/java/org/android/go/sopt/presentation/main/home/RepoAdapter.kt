package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.entity.Repo
import org.android.go.sopt.databinding.ItemRepoBinding
import org.android.go.sopt.util.DiffCallback

class RepoAdapter(context: Context) : ListAdapter<Repo, RecyclerView.ViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(ItemRepoBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) holder.setRepo(getItem(position))
    }

    class RepoViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRepo(repo: Repo) {
            binding.data = repo
        }
    }

    companion object {
        private val diffUtil = DiffCallback<Repo>(
            onItemsTheSame = { old, new -> old.name == new.name && old.author == new.author },
            onContentsTheSame = { old, new -> old == new },
        )
    }
}

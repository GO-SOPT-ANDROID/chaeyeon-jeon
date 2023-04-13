package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.data.entity.Repo
import org.android.go.sopt.databinding.HeaderHomeRepoBinding
import org.android.go.sopt.databinding.ItemHomeRepoBinding
import org.android.go.sopt.util.DiffCallback

class RepoAdapter(context: Context) : ListAdapter<Repo, RecyclerView.ViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> RepoHeaderViewHolder(
                HeaderHomeRepoBinding.inflate(
                    inflater,
                    parent,
                    false,
                ),
            )
            VIEW_TYPE_ITEM -> RepoViewHolder(ItemHomeRepoBinding.inflate(inflater, parent, false))
            else -> throw ClassCastException(
                parent.context.getString(
                    R.string.view_type_error_msg,
                    viewType,
                ),
            )
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + HEADER_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) holder.setRepo(getItem(position - HEADER_COUNT))
    }

    class RepoViewHolder(private val binding: ItemHomeRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRepo(repo: Repo) {
            binding.data = repo
        }
    }

    class RepoHeaderViewHolder(binding: HeaderHomeRepoBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1

        private const val HEADER_COUNT = 1

        private val diffUtil = DiffCallback<Repo>(
            onItemsTheSame = { old, new -> old.name == new.name && old.author == new.author },
            onContentsTheSame = { old, new -> old == new },
        )
    }
}

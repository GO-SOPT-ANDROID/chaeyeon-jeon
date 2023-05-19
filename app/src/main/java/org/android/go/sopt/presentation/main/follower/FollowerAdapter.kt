package org.android.go.sopt.presentation.main.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.databinding.HeaderFollowerBinding
import org.android.go.sopt.databinding.ItemFollowerBinding
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.util.DiffCallback

class FollowerAdapter : ListAdapter<Follower, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> FollowerHeaderViewHolder(
                HeaderFollowerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )

            VIEW_TYPE_ITEM -> FollowerViewHolder(
                ItemFollowerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )

            else -> throw ClassCastException(
                parent.context.getString(
                    R.string.view_type_error_msg,
                    viewType,
                ),
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowerViewHolder) holder.setFollower(getItem(position - HEADER_COUNT))
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + HEADER_COUNT
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    class FollowerHeaderViewHolder(private val binding: HeaderFollowerBinding) :
        RecyclerView.ViewHolder(binding.root)

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setFollower(follower: Follower) {
            binding.data = follower
        }
    }

    companion object {
        private val diffUtil = DiffCallback<Follower>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new },
        )

        private const val HEADER_COUNT = 1

        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }
}

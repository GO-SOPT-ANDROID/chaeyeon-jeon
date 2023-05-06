package org.android.go.sopt.presentation.main.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGalleryImageBinding
import org.android.go.sopt.util.DiffCallback

class ImageAdapter : ListAdapter<Int, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            ItemGalleryImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) holder.setImage(getItem(position))
    }

    class ImageViewHolder(private val binding: ItemGalleryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setImage(@DrawableRes index: Int) {
            // TODO : 이미지 연결
        }
    }

    companion object {
        private val diffUtil = DiffCallback<Int>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        )
    }
}

package com.example.myphotogrid_1.overview
import android.widget.GridView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myphotogrid_1.databinding.GridViewItemBinding
import com.example.myphotogrid_1.network.GridPhoto

// implements a Recycler view and list adapter which uses data
// binding to present list data including computing diffs b/w lists

class PhotoGridAdapter:
    ListAdapter<GridPhoto,PhotoGridAdapter.GridPhotosViewHolder>(DiffCallback) {
        // GridPhotosViewHolder takes binding variable from GridViewItem and gives access to full GridPhoto

        class GridPhotosViewHolder(
            private var binding: GridViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind (gridPhoto: GridPhoto) {
                binding.photo = gridPhoto
                // imp because it forces data binding to execute immediately, allows recycler view to correct size measures
                binding.executePendingBindings()
            }

        }

    // allows RecyclerView to determine which items have changed when list of Gridphotos updates
    companion object DiffCallback: DiffUtil.ItemCallback<GridPhoto>(){
        override fun areItemsTheSame(oldItem:GridPhoto, newItem: GridPhoto): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem:GridPhoto, newItem: GridPhoto): Boolean {
            return oldItem.url == newItem.url
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridPhotosViewHolder {
        return GridPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(
        holder: GridPhotosViewHolder,
        position: Int) {
        val gridPhoto = getItem(position)
        holder.bind(gridPhoto)
    }
}



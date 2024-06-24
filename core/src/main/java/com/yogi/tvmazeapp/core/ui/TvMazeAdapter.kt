package com.yogi.tvmazeapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.tvmazeapp.core.R
import com.yogi.tvmazeapp.core.databinding.ItemListShowBinding
import com.yogi.tvmazeapp.core.domain.model.TvMaze

class TvMazeAdapter : RecyclerView.Adapter<TvMazeAdapter.ListViewHolder>() {

    private val listData = ArrayList<TvMaze>()
    var onItemClick: ((TvMaze) -> Unit)? = null

    fun setData(newListData: List<TvMaze>?) {
        if (newListData == null) return

        val diffCallback = TvMazeDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_show, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListShowBinding.bind(itemView)
        fun bind(data: TvMaze) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemName.text = data.name
                tvItemGenres.text = data.genres
                root.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    private class TvMazeDiffCallback(
        private val oldList: List<TvMaze>,
        private val newList: List<TvMaze>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
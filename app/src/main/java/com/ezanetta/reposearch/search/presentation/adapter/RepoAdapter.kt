package com.ezanetta.reposearch.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ezanetta.reposearch.databinding.RepoItemLayoutBinding
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.presentation.adapter.viewholder.RepoItemViewHolder

class RepoAdapter : PagingDataAdapter<RepoItem, RepoItemViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = RepoItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return RepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bindRepo(it) }
    }

    fun isEmpty() = itemCount == 0

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepoItem>() {
            override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean =
                oldItem == newItem
        }
    }
}
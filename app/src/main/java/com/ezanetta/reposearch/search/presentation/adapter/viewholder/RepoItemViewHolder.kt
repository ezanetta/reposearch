package com.ezanetta.reposearch.search.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ezanetta.reposearch.R
import com.ezanetta.reposearch.databinding.RepoItemLayoutBinding
import com.ezanetta.reposearch.search.data.model.RepoItem

class RepoItemViewHolder(private val binding: RepoItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindRepo(repoItem: RepoItem) {
        binding.repoName.text = repoItem.name
        binding.ownerImage.load(repoItem.owner.avatarUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_account_circle_24)
        }
    }
}
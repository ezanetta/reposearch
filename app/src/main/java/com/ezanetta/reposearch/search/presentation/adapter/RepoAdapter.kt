package com.ezanetta.reposearch.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezanetta.reposearch.databinding.RepoItemLayoutBinding
import com.ezanetta.reposearch.search.data.model.RepoItem
import com.ezanetta.reposearch.search.presentation.adapter.viewholder.RepoItemViewHolder

class RepoAdapter(private val repoList: List<RepoItem>) :
    RecyclerView.Adapter<RepoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = RepoItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return RepoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.bindRepo(repoList[position])
    }

    override fun getItemCount() = repoList.size
}